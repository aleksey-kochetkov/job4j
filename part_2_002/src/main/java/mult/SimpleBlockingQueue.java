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
        if (this.queue.isEmpty()) {
            this.notify();
        }
        this.queue.offer(value);
    }

    public synchronized T poll() {
        while (this.queue.isEmpty()) {
            try {
                this.wait();
            } catch (InterruptedException exception) {
                throw new RuntimeException(exception);
            }
        }
        return this.queue.poll();
    }
}
