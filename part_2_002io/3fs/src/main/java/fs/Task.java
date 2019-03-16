package fs;

import java.util.Queue;
import java.util.List;
import java.util.ArrayList;
import java.util.concurrent.Callable;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.Semaphore;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;
import java.io.File;

public class Task implements Callable<List<File>> {
    private final AtomicInteger counter;
    private final Semaphore semaphore;
    private final ExecutorService pool;
    private final Queue<Future<List<File>>> futures;
    private final File parent;
    private final List<String> exts;

    public Task(Semaphore semaphore, AtomicInteger counter,
                ExecutorService pool, Queue<Future<List<File>>> futures,
                File parent, List<String> exts) {
        counter.getAndIncrement();
        this.counter = counter;
        this.semaphore = semaphore;
        this.pool = pool;
        this.parent = parent;
        this.exts = exts;
        this.futures = futures;
    }

    @Override
    public List<File> call() {
        List<File> result = this.files();
        if (this.counter.decrementAndGet() == 0) {
            this.semaphore.release();
        }
        return result;
    }

    public List<File> files() {
        List<File> result = new ArrayList<>();
        if (this.parent.isDirectory()) {
            for (File f : this.parent.listFiles()) {
                this.futures.add(
                  this.pool.submit(new Task(this.semaphore, this.counter,
                                this.pool, this.futures, f, this.exts)));
            }
        } else {
                String ext = this.parent.getName();
                ext = ext.substring(ext.lastIndexOf('.') + 1,
                                                           ext.length());
                if (this.exts.contains(ext)) {
                    result.add(this.parent);
                }
        }
        return result;
    }
}
