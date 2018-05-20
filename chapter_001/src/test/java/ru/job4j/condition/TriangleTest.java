package ru.job4j.condition;

import static org.junit.Assert.assertThat;
import static org.hamcrest.number.IsCloseTo.closeTo;
import org.junit.Test;

/**
 * @author Aleksey Kochetkov
 */
public class TriangleTest {

    /**
     * Тест на прямоугольном треугольнике с длинами катетов 1 и 2.
     */
    @Test
    public void whenGivenSidesThenHalfperimeter() {
        Point a = new Point(1, 1);
        Point b = new Point(1, 2);
        Point c = new Point(3, 1);
        Triangle triangle = new Triangle(a, b, c);
        double ab = a.distanceTo(b);
        double ac = a.distanceTo(c);
        double bc = b.distanceTo(c);
        double expected = (1 + 2 + Math.sqrt(5)) / 2;
        assertThat(triangle.period(ab, ac, bc), closeTo(expected, 0.0));
    }

    @Test
    public void whenAreaSetThreePointsThenTriangleArea() {
        Triangle triangle = new Triangle(
                      new Point(0, 0), new Point(0, 2), new Point(2, 0));
        double result = triangle.area();
        assertThat(result, closeTo(2D, 7E-16));
    }

    @Test
    public void whenPointsOnOneLineThenDegenerated() {
        Triangle triangle = new Triangle(
                      new Point(1, 3), new Point(1, 2), new Point(1, 3));
        double result = triangle.area();
        assertThat(result, closeTo(0D, 0.0));
    }
}
