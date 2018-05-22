package ru.job4j.array;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;
import org.junit.Test;

public class CheckTest {

    @Test
    public void whenDataMonoByTrueThenTrue() {
        Check check = new Check();
        boolean[] input = {true, true, true};
        assertTrue(check.mono(input));
    }

    @Test
    public void whenDataNotMonoThenFalse() {
        Check check = new Check();
        boolean[] input = {true, false, true};
        assertFalse(check.mono(input));
    }

    @Test
    public void whenDataMonoByFalseThenTrue() {
        Check check = new Check();
        boolean[] input = {false, false, false};
        assertTrue(check.mono(input));
    }
}
