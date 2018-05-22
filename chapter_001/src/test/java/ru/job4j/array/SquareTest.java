package ru.job4j.array;

import static org.junit.Assert.assertThat;
import static org.hamcrest.core.Is.is;
import org.junit.Test;

public class SquareTest {

    @Test
    public void whenBound3Then149() {
        int bound = 3;
        Square square = new Square();
        int[] result = square.calculate(bound);
        int[] expect = {1, 4, 9};
        assertThat(result, is(expect));
    }

    @Test
    public void whenBound4Then1to16() {
        int bound = 4;
        Square square = new Square();
        int[] result = square.calculate(bound);
        int[] expect = {1, 4, 9, 16};
        assertThat(result, is(expect));
    }

    @Test
    public void whenBound0ThenEmpty() {
        int bound = 0;
        Square square = new Square();
        int[] result = square.calculate(bound);
        int[] expect = new int[0];
        assertThat(result, is(expect));
    }
}
