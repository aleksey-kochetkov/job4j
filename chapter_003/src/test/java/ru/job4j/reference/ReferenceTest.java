package ru.job4j.reference;

import org.junit.Test;
import java.util.Arrays;
import static org.junit.Assert.assertThat;
import static org.hamcrest.core.Is.is;

public class ReferenceTest {

    @Test
    public void whenAscendingResult() {
        Reference reference = new Reference();
        String[] departments = {"K1\\SK1",
                                "K1\\SK2",
                                "K1\\SK1\\SSK1",
                                "K1\\SK1\\SSK2",
                                "K2",
                                "K2\\SK1\\SSK1",
                                "K2\\SK1\\SSK2"};
        reference.addAll(departments);
        String[] expect = {"K1",
                           "K1\\SK1",
                           "K1\\SK1\\SSK1",
                           "K1\\SK1\\SSK2",
                           "K1\\SK2",
                           "K2",
                           "K2\\SK1",
                           "K2\\SK1\\SSK1",
                           "K2\\SK1\\SSK2"};
        assertThat(reference.toArray(false), is(expect));
    }

    @Test
    public void whenDescendingResult() {
        Reference reference = new Reference();
        String[] departments = {"K1\\SK1",
                                "K1\\SK2",
                                "K1\\SK1\\SSK1",
                                "K1\\SK1\\SSK2",
                                "K2",
                                "K2\\SK1\\SSK1",
                                "K2\\SK1\\SSK2"};
        reference.addAll(departments);
        String[] expect = {"K2",
                           "K2\\SK1",
                           "K2\\SK1\\SSK2",
                           "K2\\SK1\\SSK1",
                           "K1",
                           "K1\\SK2",
                           "K1\\SK1",
                           "K1\\SK1\\SSK2",
                           "K1\\SK1\\SSK1"};
        assertThat(reference.toArray(true), is(expect));
    }

    @Test
    public void whenServicesIsNull() {
        Reference reference = new Reference();
        String[] departments = {"K1",
                                "K2",
                                "K2\\SK1\\SSK1",
                                "K2\\SK1\\SSK2"};
        reference.addAll(departments);
        String[] expect = {"K1",
                           "K2",
                           "K2\\SK1",
                           "K2\\SK1\\SSK1",
                           "K2\\SK1\\SSK2"};
        assertThat(reference.toArray(false), is(expect));
    }
}
