package switcher;

import net.jcip.annotations.GuardedBy;
import net.jcip.annotations.ThreadSafe;

@ThreadSafe
public class Switcher {
    @GuardedBy("this.string")
    private final StringOfIntegers string = new StringOfIntegers();
    private Thread oneThread;
    private Thread twoThread;

    public void start() {
        Runnable one = () -> this.run(1);
        Runnable two = () -> this.run(2);
        this.oneThread = new Thread(one);
        this.twoThread = new Thread(two);
        synchronized (this.string) {
            this.oneThread.start();
            try {
                this.string.wait();
            } catch (InterruptedException exception) {
                throw new RuntimeException(exception);
            }
        }
        this.twoThread.start();
    }

    private void run(int value) {
// Это тестовый блок, оставил для наглядности. Он задерживает первый
// поток, чтоб было чётко видно, что второй поток всё-равно не станет
// наполнять строку раньше первого потока
//        if (value == 1) {
//            try {
//                Thread.sleep(3000);
//            } catch (InterruptedException exception) {
//                throw new RuntimeException(exception);
//            }
//        }
        while (!Thread.interrupted()) {
            synchronized (this.string) {
                for (int i = 0; i < 10; i++) {
                    this.string.append(value);
                }
                this.string.notifyAll();
                try {
                    this.string.wait();
                } catch (InterruptedException exception) {
                    break;
                }
            }
        }
    }

    public void stop() {
        this.oneThread.interrupt();
        this.twoThread.interrupt();
        try {
            this.oneThread.join();
            this.twoThread.join();
        } catch (InterruptedException exception) {
            throw new RuntimeException(exception);
        }
    }

    String getResult() {
        synchronized (this.string) {
            return this.string.get();
        }
    }
}
