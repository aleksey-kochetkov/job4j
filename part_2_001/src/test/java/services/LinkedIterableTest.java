package services;

import java.util.ConcurrentModificationException;
import java.util.NoSuchElementException;
import org.junit.Test;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;
import static org.hamcrest.core.Is.is;

public class LinkedIterableTest {

    @Test
    public void whenAdd() {
        LinkedIterable<Integer> iterable = new LinkedIterable<>();
        for (int i = 0; i <= 10; i++) {
            iterable.add(i);
        }
        for (int i = 0; i <= 10; i++) {
            assertThat(iterable.get(i), is(i));
        }
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void whenGetWithIndexTooHigh() {
        LinkedIterable<Integer> iterable = new LinkedIterable<>();
        iterable.get(0);
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void whenGetWithIndexNegative() {
        LinkedIterable<Integer> iterable = new LinkedIterable<>();
        iterable.get(-8);
    }

    @Test
    public void whenIterate() {
        LinkedIterable<String> iterable = new LinkedIterable<>();
        iterable.add("one");
        iterable.add("two");
        String result = null;
        for (String s : iterable) {
            result = s;
        }
        assertThat(result, is("two"));
    }

    @Test(expected = ConcurrentModificationException.class)
    public void whenIterateThenFailFastBehaviour() {
        LinkedIterable<String> iterable = new LinkedIterable<>();
        iterable.add("one");
        iterable.add("two");
        for (String s : iterable) {
            iterable.add("three");
        }
    }

    @Test(expected = NoSuchElementException.class)
    public void whenExcessiveNextThenNoSuchElementException() {
        LinkedIterable<String> iterable = new LinkedIterable<>();
        iterable.iterator().next();
    }

    @Test
    public void whenRemoveThenLowerSize() {
        LinkedIterable<String> iterable = new LinkedIterable<>();
        iterable.add("one");
        iterable.add("two");
        iterable.remove(0);
        assertEquals(1, iterable.size());
    }

    @Test
    public void whenRemoveInTheMiddleThenLowerSize() {
        LinkedIterable<String> iterable = new LinkedIterable<>();
        iterable.add("one");
        iterable.add("two");
        iterable.add("three");
        iterable.remove(2);
        assertEquals(2, iterable.size());
    }
}
