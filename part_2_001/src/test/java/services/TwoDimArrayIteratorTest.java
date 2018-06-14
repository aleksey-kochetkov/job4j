package services;

import org.junit.Test;
import java.util.Iterator;
import java.util.NoSuchElementException;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertEquals;

public class TwoDimArrayIteratorTest {

    @Test
    public void whenFirstSample() {
        int[][] input = {{1, 2},
                         {3, 4}};
        Iterator iterator = new TwoDimArrayIterator(input);
        assertEquals(1, iterator.next());
        assertEquals(2, iterator.next());
        assertEquals(3, iterator.next());
        assertEquals(4, iterator.next());
    }

    @Test
    public void whenSecondSample() {
        int[][] input =
                  {{1}, {2, 3, 4, 5}, {6, 7}, {8, 9, 10, 11, 12, 13, 14}};
        Iterator iterator = new TwoDimArrayIterator(input);
        for (int i = 1; i <= 14; i++) {
            assertEquals(i, iterator.next());
        }
    }

    @Test
    public void whenMySample() {
        int[][] input =
                  {{1}, {}, {2, 3}, {4, 5, 6, 7, 8, 9, 10}};
        Iterator iterator = new TwoDimArrayIterator(input);
        for (int i = 1; i <= 10; i++) {
            assertEquals(i, iterator.next());
        }
    }

    @Test
    public void whenEmptyThenNotHasNext() {
        int[][] input = new int[0][0];
        Iterator iterator = new TwoDimArrayIterator(input);
        assertFalse(iterator.hasNext());
    }

    @Test(expected = NoSuchElementException.class)
    public void whenExcessiveNextThenNoSuchElementException() {
        int[][] input = {{1}};
        Iterator iterator = new TwoDimArrayIterator(input);
        iterator.next();
        iterator.next();
    }
}
