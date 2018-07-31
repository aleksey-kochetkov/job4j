package mult;

import net.jcip.annotations.ThreadSafe;
import net.jcip.annotations.GuardedBy;

import java.util.Queue;
import java.util.LinkedList;

@ThreadSafe
public class SimpleBlockingQueue<T> {

    @GuardedBy("this")
    private Queue<T> queue = new LinkedList<>();

    public synchronized void offer(T value) {
        this.queue.offer(value);
        this.notify();
    }

    public synchronized T poll() {
        T result;
        do {
            result = this.queue.poll();
            if (result == null) {
                try {
                    this.wait();
                } catch (InterruptedException exception) {
                    throw new RuntimeException(exception);
                }
            }
        } while (result == null);
        return result;
    }
}
