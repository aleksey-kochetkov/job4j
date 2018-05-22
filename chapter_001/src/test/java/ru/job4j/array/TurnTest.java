package ru.job4j.array;

import static org.junit.Assert.assertThat;
import static org.hamcrest.core.Is.is;
import org.junit.Test;

public class TurnTest {

    @Test
    public void whenTurnArrayWithEvenAmountOfElementsThenTurnedArray() {
        Turn turn = new Turn();
        int[] input = {4, 1, 6, 2};
        int[] result = turn.back(input);
        int[] expect = {2, 6, 1, 4};
        assertThat(result, is(expect));
    }

    @Test
    public void whenTurnArrayWithOddAmountOfElementsThenTurnedArray() {
        Turn turn = new Turn();
        int[] input = {1, 2, 3, 4, 5};
        int[] result = turn.back(input);
        int[] expect = {5, 4, 3, 2, 1};
        assertThat(result, is(expect));
    }
}
