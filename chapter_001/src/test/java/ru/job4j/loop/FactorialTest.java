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

    @Test
    public void when0Then1() {
        Factorial factorial = new Factorial();
        assertEquals(1, factorial.calc(0));
    }

    /**
     * Некорреrтный параметр
     */
    @Test
    public void whenLT0ThenMinus1() {
        Factorial factorial = new Factorial();
        assertEquals(-1, factorial.calc(-100));
    }
}
