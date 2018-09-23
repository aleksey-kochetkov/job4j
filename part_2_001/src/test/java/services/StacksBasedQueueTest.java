package services;

import org.junit.Test;
import static org.junit.Assert.assertThat;
import static org.hamcrest.core.Is.is;

public class StacksBasedQueueTest {
    @Test
    public void whenPushPollThenFIFO() {
        StacksBasedQueue<Integer> queue = new StacksBasedQueue<>();
        int[] expect = {0, 1, 2, 3};
        int[] result = new int[4];
        queue.push(0);
        queue.push(1);
        result[0] = queue.poll();
        queue.push(2);
        queue.push(3);
        result[1] = queue.poll();
        result[2] = queue.poll();
        result[3] = queue.poll();
        assertThat(result, is(expect));
    }
}
