package multithreads;

import net.jcip.annotations.GuardedBy;
import net.jcip.annotations.ThreadSafe;
import java.io.IOException;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Pattern;
import java.nio.file.Files;
import java.nio.file.FileVisitor;
import java.nio.file.Path;
import java.nio.file.FileVisitResult;
import java.nio.file.attribute.BasicFileAttributes;
import java.nio.file.Paths;

@ThreadSafe
public class ParallelSearch {
    private final String root;
    private final String text;
    private final List<String> exts;

    @GuardedBy("this")
    private final Queue<String> files = new LinkedList<>();

    @GuardedBy("this")
    private final List<String> paths = new ArrayList<>();

    private volatile boolean finish;


    public ParallelSearch(String root, String text, List<String> exts) {
        this.root = root;
        this.text = text;
        this.exts = exts;
    }

    public void init() {
        Thread search = new Thread() {
            @Override
            public void run() {
                try {
                    Files.walkFileTree(Paths.get(ParallelSearch.this.root), new FileVisitor<Path>() {
                        @Override
                        public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attrs) {
                            return FileVisitResult.CONTINUE;
                        }

                        @Override
                        public FileVisitResult postVisitDirectory(Path dir, IOException exception) {
                            return FileVisitResult.CONTINUE;
                        }

                        @Override
                        public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) {
                            for (String ext : ParallelSearch.this.exts) {
                                if (file.toString().endsWith(ext)) {
                                    synchronized (ParallelSearch.this) {
                                        if (ParallelSearch.this.files.isEmpty()) {
                                            ParallelSearch.this.notifyAll();
                                        }
                                        ParallelSearch.this.files.offer(file.toString());
                                    }
                                }
                            }
                            return FileVisitResult.CONTINUE;
                        }

                        @Override
                        public FileVisitResult visitFileFailed(Path file,
                                                               IOException exception) throws IOException {
                            throw exception;
                        }
                    });
                } catch (IOException exception) {
                    throw new RuntimeException(exception);
                }
                ParallelSearch.this.finish = true;
                synchronized (ParallelSearch.this) {
                    ParallelSearch.this.notifyAll();
                }
            }
        };
        Thread reader = new Thread() {
            private Pattern text = Pattern.compile(ParallelSearch.this.text);

            @Override
            public void run() {
                while (true) {
                    String filePath;
                    synchronized (ParallelSearch.this) {
                        if (ParallelSearch.this.files.isEmpty()) {
                            if (ParallelSearch.this.finish) {
                                break;
                            }
                            try {
                                ParallelSearch.this.wait();
                            } catch (InterruptedException exception) {
                                throw new RuntimeException(exception);
                            }
                            if (ParallelSearch.this.files.isEmpty()) {
                                break;
                            }
                        }
                        filePath = ParallelSearch.this.files.poll();
                    }
                    if (this.hasText(filePath)) {
                        synchronized (ParallelSearch.this) {
                            ParallelSearch.this.paths.add(filePath);
                        }
                    }
                }
            }

            private boolean hasText(String filePath) {
                boolean result;
                try {
                    try (Scanner scanner = new Scanner(new File(filePath))) {
                        result = (scanner.findWithinHorizon(this.text, 0) != null);
                    }
                } catch (FileNotFoundException exception) {
                    throw new RuntimeException(exception);
                }
                return result;
            }
        };
        search.start();
        reader.start();
        try {
            search.join();
            reader.join();
        } catch (InterruptedException exception) {
            throw new RuntimeException(exception);
        }
    }

    synchronized public List<String> result() {
        return this.paths;
    }
}