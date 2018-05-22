package ru.job4j.array;

import static org.junit.Assert.assertThat;
import static org.hamcrest.core.Is.is;
import org.junit.Test;

public class FindLoopTest {

    @Test
    public void whenArrayHasLength5Then0() {
        FindLoop find = new FindLoop();
        int[] input = {5, 10, 3};
        int value = 5;
        int result = find.indexOf(input, value);
        int expect = 0;
        assertThat(result, is(0));
    }

    @Test
    public void whenArrayHasLength6ThenMinus1() {
        FindLoop find = new FindLoop();
        int[] input = {5, 10, 3};
        int value = 6;
        int result = find.indexOf(input, value);
        int expect = -1;
        assertThat(result, is(expect));
    }

    @Test
    public void whenArrayIsEmpty5ThenMinus1() {
        FindLoop find = new FindLoop();
        int[] input = new int[0];
        int value = 5;
        int result = find.indexOf(input, value);
        int expect = -1;
        assertThat(result, is(expect));
    }
}
