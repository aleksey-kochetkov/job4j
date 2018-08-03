package pool;

import java.util.Queue;
import java.util.List;
import java.util.LinkedList;
import java.util.concurrent.LinkedBlockingQueue;

public class ThreadPool {
    private final List<Thread> threads = new LinkedList<>();
    private final LinkedBlockingQueue<Runnable> tasks = new LinkedBlockingQueue<>();

    public void start() {
        for (int i = 0; i < Runtime.getRuntime().availableProcessors(); i++) {
            Thread thread = new Thread() {
                @Override
                public void run() {
                    while (!Thread.currentThread().isInterrupted()) {
                        try {
                            ThreadPool.this.tasks.take().run();
                        } catch (InterruptedException exception) {
                            throw new RuntimeException(exception);
                        }
                    }
                }
            };
            this.threads.add(thread);
            thread.start();
        }
        for (Thread thread : this.threads) {
            try {
                thread.join();
            } catch (InterruptedException exception) {
                throw new RuntimeException(exception);
            }
        }
    }

    public void work(Runnable job) {
        try {
            this.tasks.put(job);
        } catch (InterruptedException exception) {
            throw new RuntimeException(exception);
        }
    }

    public void shutdown() {
        for (Thread thread : this.threads) {
            thread.interrupt();
        }
    }
}