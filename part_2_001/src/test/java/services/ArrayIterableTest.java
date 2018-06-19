package services;

import java.util.ConcurrentModificationException;
import java.util.NoSuchElementException;
import org.junit.Test;
import static org.junit.Assert.assertThat;
import static org.hamcrest.core.Is.is;

public class ArrayIterableTest {

    @Test
    public void whenAdd() {
        ArrayIterable<Integer> iterable = new ArrayIterable<>();
        for (int i = 0; i <= 10; i++) {
            iterable.add(i);
        }
        for (int i = 0; i <= 10; i++) {
            assertThat(iterable.get(i), is(i));
        }
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void whenGetWithIllegalIndex() {
        ArrayIterable<Integer> iterable = new ArrayIterable<>();
        iterable.get(0);
    }

    @Test
    public void whenIterate() {
        ArrayIterable<String> iterable = new ArrayIterable<>();
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
        ArrayIterable<String> iterable = new ArrayIterable<>();
        iterable.add("one");
        for (String s : iterable) {
            iterable.add("two");
        }
    }

    @Test(expected = NoSuchElementException.class)
    public void whenExcessiveNextThenNoSuchElementException() {
        ArrayIterable<String> iterable = new ArrayIterable<>();
        iterable.iterator().next();
    }

    @Test
    public void whenSecondGrowBugThenCorrected() {
        ArrayIterable<Integer> iterable = new ArrayIterable<>();
        for (int i = 0; i < 20; i++) {
            iterable.add(i);
        }
        for (int i = 0; i < 20; i++) {
            assertThat(iterable.get(i), is(i));
        }
    }
}
