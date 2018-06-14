package services;

import org.junit.Test;
import java.util.Iterator;
import java.util.NoSuchElementException;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertEquals;

public class TwoDimArrayIterableTest {

    @Test
    public void whenFirstSample() {
        int[][] input = {{1, 2},
                         {3, 4}};
        TwoDimArrayIterable iterable = new TwoDimArrayIterable(input);
        Iterator iterator = iterable.iterator();
        assertEquals(1, iterator.next());
        assertEquals(2, iterator.next());
        assertEquals(3, iterator.next());
        assertEquals(4, iterator.next());
    }

    @Test
    public void whenSecondSample() {
        int[][] input =
                  {{1}, {2, 3, 4, 5}, {6, 7}, {8, 9, 10, 11, 12, 13, 14}};
        TwoDimArrayIterable iterable = new TwoDimArrayIterable(input);
        Iterator iterator = iterable.iterator();
        for (int i = 1; i <= 14; i++) {
            assertEquals(i, iterator.next());
        }
    }

    @Test
    public void whenMySample() {
        int[][] input =
                  {{1}, {}, {2, 3}, {4, 5, 6, 7, 8, 9, 10}};
        TwoDimArrayIterable iterable = new TwoDimArrayIterable(input);
        Iterator iterator = iterable.iterator();
        for (int i = 1; i <= 10; i++) {
            assertEquals(i, iterator.next());
        }
    }

    @Test
    public void whenEmptyThenNotHasNext() {
        int[][] input = new int[0][0];
        TwoDimArrayIterable iterable = new TwoDimArrayIterable(input);
        Iterator iterator = iterable.iterator();
        assertFalse(iterator.hasNext());
    }

    @Test(expected = NoSuchElementException.class)
    public void whenExcessiveNextThenNoSuchElementException() {
        int[][] input = {{1}};
        TwoDimArrayIterable iterable = new TwoDimArrayIterable(input);
        Iterator iterator = iterable.iterator();
        iterator.next();
        iterator.next();
    }
}
