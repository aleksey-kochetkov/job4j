package ru.job4j.array;

import static org.junit.Assert.assertThat;
import static org.hamcrest.core.Is.is;
import org.junit.Test;

public class ArrayMergeTest {
    @Test
    public void whenSomeInputThenResult() {
        ArrayMerge arrayMerge = new ArrayMerge();
        int[] one = {1, 3, 4, 8};
        int[] two = {2, 5, 6, 7, 9, 10};
        int[] result = arrayMerge.merge(one, two);
        int[] expect = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        assertThat(result, is(expect));
    }
}
