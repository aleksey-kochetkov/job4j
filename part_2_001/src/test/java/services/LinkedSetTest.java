package services;


import java.util.NoSuchElementException;
import java.util.ConcurrentModificationException;
import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class LinkedSetTest {

    @Test
    public void whenAddDoubleThenNoDoubles() {
        LinkedSet<String> set = new LinkedSet<>();
        set.add("one");
        set.add("two");
        set.add("two");
        int result = 0;
        for (String s : set) {
            result++;
        }
        assertEquals(2, result);
    }

    @Test
    public void whenAddDoubleNullThenContainsOneNull() {
        LinkedSet<String> set = new LinkedSet<>();
        set.add(null);
        set.add("two");
        set.add(null);
        int result = 0;
        for (String s : set) {
            result++;
        }
        assertEquals(2, result);
    }

    @Test(expected = NoSuchElementException.class)
    public void whenExcessiveIterationThenNoSuchElementException() {
        LinkedSet<String> set = new LinkedSet<>();
        set.iterator().next();
    }

    @Test(expected = ConcurrentModificationException.class)
    public void whenAddWithinIterationThenNoConcurrentModificationException() {
        LinkedSet<String> set = new LinkedSet<>();
        set.add("one");
        set.add("two");
        for (String s : set) {
            set.add("three");
        }
    }
}
