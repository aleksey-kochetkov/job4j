package ru.job4j.array;

import static org.junit.Assert.assertThat;
import static org.hamcrest.core.Is.is;
import org.junit.Test;

public class ArrayMergeTest {
    @Test
    public void whenSomeInputThenResult() {
        ArrayMerge arrayMerge = new ArrayMerge();
        String[] one = {"Eight", "One", "Three", "Two"};
        String[] two = {"Five", "Four", "Nine", "Seven", "Six", "Ten"};
        String[] result = arrayMerge.merge(one, two);
        String[] expect = {"Eight", "Five", "Four", "Nine", "One",
                                  "Seven", "Six", "Ten", "Three", "Two"};
        assertThat(result, is(expect));
    }
}
