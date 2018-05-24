package ru.job4j.array;

import static org.junit.Assert.assertThat;
import static org.hamcrest.collection.IsArrayContainingInAnyOrder.arrayContainingInAnyOrder;
import org.junit.Test;

public class ArrayDuplicateTest {

    @Test
    public void whenRemoveDuplicateThenArrayWithoutDuplicate() {
        ArrayDuplicate ad = new ArrayDuplicate();
        String[] input = {"Привет", "Мир", "Привет", "Супер", "Мир"};
        String[] expect = {"Привет", "Мир", "Супер"};
        assertThat(ad.remove(input), arrayContainingInAnyOrder(expect));
    }

    @Test
    public void whenDegeneratedSetThenOneElement() {
        ArrayDuplicate ad = new ArrayDuplicate();
        String[] input = {"Element", "Element", "Element"};
        String[] expect = {"Element"};
        assertThat(ad.remove(input), arrayContainingInAnyOrder(expect));
    }

    @Test
    public void whenLotOfTailDoublesThenJustAnotherTest() {
        ArrayDuplicate ad = new ArrayDuplicate();
        String[] input = {"One", "Element", "One", "Element", "Element", "Element", "Element"};
        String[] expect = {"One", "Element"};
        assertThat(ad.remove(input), arrayContainingInAnyOrder(expect));
    }
}
