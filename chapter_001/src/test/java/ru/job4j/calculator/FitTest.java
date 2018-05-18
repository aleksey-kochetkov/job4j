package ru.job4j.calculator;

import static org.junit.Assert.assertThat;
import static org.hamcrest.number.IsCloseTo.closeTo;
import org.junit.Test;

public class FitTest {

    @Test
    public void manWeight() {
        Fit fit = new Fit();
        double weight = fit.manWeight(180);
        assertThat(weight, closeTo(92, 0.1));
    }

    @Test
    public void womanWeight() {
        Fit fit = new Fit();
        double weight = fit.womanWeigth(170);
        assertThat(weight, closeTo(69, 0.1));
    }
}
