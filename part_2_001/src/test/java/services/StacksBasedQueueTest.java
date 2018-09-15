package services;

import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class StacksBasedQueueTest {
    @Test
    public void whenPushPollThenFIFO() {
        StacksBasedQueue<Integer> queue = new StacksBasedQueue<>();
        queue.push(0);
        queue.push(1);
        assertEquals(queue.poll().intValue(), 0);
        queue.push(2);
        queue.push(3);
        assertEquals(queue.poll().intValue(), 1);
        assertEquals(queue.poll().intValue(), 2);
        assertEquals(queue.poll().intValue(), 3);
    }
}
