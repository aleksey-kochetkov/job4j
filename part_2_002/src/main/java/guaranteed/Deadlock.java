package guaranteed;

import java.util.concurrent.CountDownLatch;

public class Deadlock {
    private CountDownLatch latch = new CountDownLatch(2);
    private Thread mainThread = Thread.currentThread();
    private Thread thread;
    private final Object resourceA = new Object();
    private final Object resourceB = new Object();

    public static void main(String[] args) {
        new Deadlock().init();
    }

    /**
     * От себя добавил ещё один поток - контрольный. Он показывает
     * состояния рабочих потоков, они должны быть BLOCKED, а не
     * WAITING, чтоб был настоящий deadlock. В контрольном потоке
     * можно использовать sleep()! ;)
     * Вот что показал контрольный поток:
     * main:BLOCKED thread:BLOCKED
     */
    private void init() {
        this.thread = new Thread() {
            @Override
            public void run() {
                synchronized (Deadlock.this.resourceB) {
                    Deadlock.this.latch.countDown();
                    try {
                        Deadlock.this.latch.await();
                        synchronized (Deadlock.this.resourceA) {
                            byte fake;
                        }
                    } catch (InterruptedException exception) {
                        throw new RuntimeException(exception);
                    }
                }
            }
        };
        startControlThread();
        thread.start();
        synchronized (this.resourceA) {
            this.latch.countDown();
            try {
                Deadlock.this.latch.await();
                synchronized (this.resourceB) {
                    byte fake;
                }
            } catch (InterruptedException exception) {
                throw new RuntimeException(exception);
            }
        }
        System.out.format("Never reached!");
    }

    /**
     * В контрольном потоке можно использовать sleep()!
     */
    private void startControlThread() {
        new Thread() {
            @Override
            public void run() {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException exception) {
                    throw new RuntimeException(exception);
                }
                System.out.format("main:%s thread:%s%n",
                            Deadlock.this.mainThread.getState(),
                            Deadlock.this.thread.getState());
            }
        }.start();
    }
}
