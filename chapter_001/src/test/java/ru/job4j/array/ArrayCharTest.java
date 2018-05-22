package ru.job4j.array;

import static org.junit.Assert.assertThat;
import static org.hamcrest.core.Is.is;
import org.junit.Test;

public class ArrayCharTest {

    @Test
    public void whenStartsWithPrefixThenTrue() {
        ArrayChar word = new ArrayChar("Hello");
        boolean result = word.startsWith("He");
        assertThat(result, is(true));
    }

    @Test
    public void whenNotStartsWithPrefixThenFalse() {
        ArrayChar word = new ArrayChar("Hello");
        boolean result = word.startsWith("Hi");
        assertThat(result, is(false));
    }
}
