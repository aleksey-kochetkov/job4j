package mult;

import org.junit.Test;
import static org.junit.Assert.assertThat;
import static org.hamcrest.core.Is.is;

public class SimpleBlockingQueueTest {
    private int[] result = new int[1000];

    @Test
    public void when() {
        SimpleBlockingQueue<Integer> queue = new SimpleBlockingQueue<>();
        Thread producer = new Thread() {
            @Override
            public void run() {
                for (int i = 0; i < 1000; i++) {
                    queue.offer(i);
                    try {
                        Thread.sleep(3);
                    } catch (InterruptedException exception) {
                        throw new RuntimeException(exception);
                    }
                }
            }
        };
        Thread consumer = new Thread() {
            @Override
            public void run() {
                int i;
                do {
                    i = queue.poll();
                    SimpleBlockingQueueTest.this.result[i] = i;
                } while (i < 1000 - 1);
            }
        };
        consumer.start();
        producer.start();
        try {
            producer.join();
            consumer.join();
        } catch (InterruptedException exception) {
            throw new RuntimeException(exception);
        }
        int[] expect = new int[1000];
        for (int i = 0; i < 1000; i++) {
            expect[i] = i;
        }
        assertThat(result, is(expect));
    }
}