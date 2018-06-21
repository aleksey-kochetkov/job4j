package list;

import org.junit.Test;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;

public class CycleDetectorTest {

    @Test
    public void whenSampleThenHasCycle() {
        Node<Integer> first = new Node<>(1);
        Node<Integer> second = new Node<>(2);
        Node<Integer> third = new Node<>(3);
        Node<Integer> fourth = new Node<>(4);
        first.next = second;
        second.next = third;
        third.next = fourth;
        fourth.next = first;
        assertTrue(new CycleDetector<Integer>().hasCycleTmp(first));
        assertTrue(new CycleDetector<Integer>().hasCycle(first));
    }

    @Test
    public void whenThenNoCycle() {
        Node<Integer> first = new Node<>(1);
        Node<Integer> second = new Node<>(2);
        Node<Integer> third = new Node<>(3);
        Node<Integer> fourth = new Node<>(4);
        first.next = second;
        second.next = third;
        third.next = fourth;
        assertFalse(new CycleDetector<Integer>().hasCycleTmp(first));
        assertFalse(new CycleDetector<Integer>().hasCycle(first));
    }
}
