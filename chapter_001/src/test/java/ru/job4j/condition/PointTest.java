package ru.job4j.condition;

import static org.junit.Assert.assertThat;
import static org.hamcrest.number.IsCloseTo.closeTo;
import org.junit.Test;

public class PointTest {

    @Test
    public void whenXYDistanceIsEqualThenSqrt2() {
        Point a = new Point(1, 1);
        Point b = new Point(2, 2);
        assertThat(a.distanceTo(b), closeTo(Math.sqrt(2), 0.0));
    }
}
