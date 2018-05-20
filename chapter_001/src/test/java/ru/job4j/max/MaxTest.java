package ru.job4j.max;

import static org.junit.Assert.assertEquals;
import org.junit.Test;

/**
 * @author Aleksey Kochetkov
 */
public class MaxTest {

    @Test
    public void whenFirstLTSecond() {
        Max max = new Max();
        assertEquals(2, max.max(1, 2));
    }

    @Test
    public void whenFirstGTSecond() {
        Max max = new Max();
        assertEquals(4, max.max(4, 3));
    }
}
