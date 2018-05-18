package ru.job4j.calculate;

import static org.junit.Assert.assertThat;
import static org.hamcrest.Matchers.is;
import org.junit.Test;

public class CalculatorTest {

    @Test
    public void whenOnePlusOneThenTwo() {
        Calculator calculator = new Calculator();
        calculator.add(1D, 1D);
        double result = calculator.getResult();
        double expected = 2D;
        assertThat(result, is(expected));
    }

    @Test
    public void whenThreeMinusOneThenTwo() {
        Calculator calculator = new Calculator();
        calculator.subtract(3D, 1D);
        double result = calculator.getResult();
        double expected = 2D;
        assertThat(result, is(expected));
    }

    @Test
    public void whenTwoByThreeThenSix() {
        Calculator calculator = new Calculator();
        calculator.multiply(2D, 3D);
        double result = calculator.getResult();
        double expected = 6D;
        assertThat(result, is(expected));
    }

    @Test
    public void whenThreeDivTwoThenOnePointFive() {
        Calculator calculator = new Calculator();
        calculator.div(3D, 2D);
        double result = calculator.getResult();
        double expected = 1.5;
        assertThat(result, is(expected));
    }
}
