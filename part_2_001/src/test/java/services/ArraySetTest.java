package services;

import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class ArraySetTest {

    @Test
    public void whenAddDoubleThenNoDoubles() {
        ArraySet<String> set = new ArraySet<>();
        set.add("one");
        set.add("two");
        set.add("one");
        int result = 0;
        for (String s : set) {
            result++;
        }
        assertEquals(2, result);
    }

    @Test
    public void whenAddDoubleNullThenContainsOneNull() {
        ArraySet<String> set = new ArraySet<>();
        set.add(null);
        set.add("two");
        set.add(null);
        int result = 0;
        for (String s : set) {
            result++;
        }
        assertEquals(2, result);
    }
}
