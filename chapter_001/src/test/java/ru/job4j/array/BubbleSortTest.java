package ru.job4j.array;

import static org.junit.Assert.assertThat;
import static org.hamcrest.core.Is.is;
import org.junit.Test;

public class BubbleSortTest {

    @Test
    public void whenSortArrayOfTenElementsThenSortedArray() {
        BubbleSort sort = new BubbleSort();
        int[] input = {1, 5, 4, 2, 3, 1, 7, 8, 0, 5};
        int[] expect = {0, 1, 1, 2, 3, 4, 5, 5, 7, 8};
        int[] result = sort.sort(input);
        assertThat(result, is(expect));
    }
}
