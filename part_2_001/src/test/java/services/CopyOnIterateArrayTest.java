package services;

import java.util.ConcurrentModificationException;
import java.util.NoSuchElementException;
import org.junit.Test;
import static org.junit.Assert.assertThat;
import static org.hamcrest.core.Is.is;

public class CopyOnIterateArrayTest {

    @Test
    public void whenAdd() {
        CopyOnIterateArray<Integer> iterable = new CopyOnIterateArray<>();
        for (int i = 0; i <= 10; i++) {
            iterable.add(i);
        }
        for (int i = 0; i <= 10; i++) {
            assertThat(iterable.get(i), is(i));
        }
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void whenGetWithIllegalIndex() {
        CopyOnIterateArray<Integer> iterable = new CopyOnIterateArray<>();
        iterable.get(0);
    }

    @Test
    public void whenIterate() {
        CopyOnIterateArray<String> iterable = new CopyOnIterateArray<>();
        iterable.add("one");
        iterable.add("two");
        String result = null;
        for (String s : iterable) {
            result = s;
        }
        assertThat(result, is("two"));
    }

    @Test
    public void whenIterateThenFailSafeBehaviour() {
        CopyOnIterateArray<String> iterable = new CopyOnIterateArray<>();
        iterable.add("one");
        for (String s : iterable) {
            iterable.add("two");
        }
    }

    @Test(expected = NoSuchElementException.class)
    public void whenExcessiveNextThenNoSuchElementException() {
        CopyOnIterateArray<String> iterable = new CopyOnIterateArray<>();
        iterable.iterator().next();
    }

    @Test
    public void whenSecondGrowBugThenCorrected() {
        CopyOnIterateArray<Integer> iterable = new CopyOnIterateArray<>();
        for (int i = 0; i < 20; i++) {
            iterable.add(i);
        }
        for (int i = 0; i < 20; i++) {
            assertThat(iterable.get(i), is(i));
        }
    }
}
