package ru.job4j.loop;

import static org.junit.Assert.assertEquals;
import org.junit.Test;

/**
 * @author Aleksey Kochetkov
 */
public class FactorialTest {
    @Test
    public void when5Then120() {
        Factorial factorial = new Factorial();
        assertEquals(120, factorial.calc(5));
    }
}
