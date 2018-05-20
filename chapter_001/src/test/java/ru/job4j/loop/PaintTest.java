package ru.job4j.loop;

import static org.junit.Assert.assertThat;
import static org.hamcrest.core.Is.is;
import org.junit.Test;
import java.util.StringJoiner;

public class PaintTest {

    @Test
    public void whenPyramid4Rigth() {
        Paint paint = new Paint();
        String result = paint.rightTrl(4);
        System.out.println(result);
        assertThat(result, is(
                new StringJoiner(System.lineSeparator(), "",  System.lineSeparator())
                    .add("^   ")
                    .add("^^  ")
                    .add("^^^ ")
                    .add("^^^^").toString()
        ));
    }

    @Test
    public void whenPyramid4Left() {
        Paint paint = new Paint();
        String result = paint.leftTrl(4);
        System.out.println(result);
        assertThat(result, is(
                new StringJoiner(System.lineSeparator(), "", System.lineSeparator())
                    .add("   ^")
                    .add("  ^^")
                    .add(" ^^^")
                    .add("^^^^").toString()
        ));
    }

    @Test
    public void whenPyramid4() {
        Paint paint = new Paint();
        String result = paint.pyramid(4);
        System.out.println(result);
    }
}
