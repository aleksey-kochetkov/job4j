package services;

import org.junit.Test;
import static org.junit.Assert.assertThat;
import static org.hamcrest.core.Is.is;

public class SimpleQueueTest {

    @Test
    public void whenAdd() {
        SimpleQueue<String> queue = new SimpleQueue<>();
        queue.add("one");
        queue.add("two");
        assertThat(queue.remove(), is("one"));
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void whenExcessiveRemove() {
        SimpleQueue<String> queue = new SimpleQueue<>();
        queue.remove();
    }
}
