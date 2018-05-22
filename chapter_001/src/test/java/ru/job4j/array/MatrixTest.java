package ru.job4j.array;

import static org.junit.Assert.assertThat;
import static org.hamcrest.core.Is.is;
import org.junit.Test;

public class MatrixTest {

    @Test
    public void when2By2() {
        Matrix matrix = new Matrix();
        int[][] table = matrix.multiple(2);
        int[][] expect = {{1, 2},
                          {2, 4}};
        assertThat(table, is(expect));
    }
}
