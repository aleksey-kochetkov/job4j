package fs;

import java.io.File;
import java.util.Queue;
import java.util.List;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Arrays;
import java.util.concurrent.ConcurrentLinkedQueue;

/**
 * Всего здесь 3 варианта реализации: однопоточный, пул потоков и
 * двухпоточный (обход файлов и отбор файлов).
 * 3) Двухпоточный
 * В этом задании нужно написать метод, который возвращает список всех
 * файлов с конкретным расширением. Метод должен заходить во все
 * каталоги. Здесь нельзя использовать FileVisitor. Это задание на работу
 * с деревом каталогов.
 * Разбиение на два потока - обход файловой системы и отбор файлов из
 * общей очереди.
 */
public class TwoThreadsSearch {
    private final Queue<File> files = new ConcurrentLinkedQueue<>();
    private volatile boolean finish;

    public List<File> files(String parent, List<String> exts)
                                            throws InterruptedException {
        new Thread(new Runnable() {
            @Override
            public void run() {
                final Queue<File> queue = new LinkedList<>();
                File f = new File(parent);
                while (f != null) {
                    if (f.isDirectory()) {
                        queue.addAll(Arrays.asList(f.listFiles()));
                    } else {
                        TwoThreadsSearch.this.files.add(f);
                    }
                    f = queue.poll();
                }
                TwoThreadsSearch.this.finish = true;
            }
        }).start();
        List<File> result = new ArrayList<>();
        do {
            boolean finish = this.finish;
            File f = files.poll();
            if (f == null) {
                if (finish) {
                    break;
                } else {
                    continue;
                }
            }
            String ext = f.getName();
            ext = ext.substring(ext.lastIndexOf('.') + 1, ext.length());
            if (exts.contains(ext)) {
                result.add(f);
            }
        } while (true);
        return result;
    }
}
