package ru.job4j.reference;

import org.junit.Test;
import static org.junit.Assert.assertThat;
import static org.hamcrest.core.Is.is;

public class DepartmentsTest {

    @Test
    public void whenAscendingResult() {
        String[] input = {"K1\\SK1",
                          "K1\\SK2",
                          "K1\\SK1\\SSK1",
                          "K1\\SK1\\SSK2",
                          "K2",
                          "K2\\SK1\\SSK1",
                          "K2\\SK1\\SSK2"};
        Departments departments = new Departments(input);
        String[] expect = {"K1",
                           "K1\\SK1",
                           "K1\\SK1\\SSK1",
                           "K1\\SK1\\SSK2",
                           "K1\\SK2",
                           "K2",
                           "K2\\SK1",
                           "K2\\SK1\\SSK1",
                           "K2\\SK1\\SSK2"};
        assertThat(departments.sortAscending().toArray(), is(expect));
    }

    @Test
    public void whenDescendingResult() {
        String[] input = {"K1\\SK1",
                          "K1\\SK2",
                          "K1\\SK1\\SSK1",
                          "K1\\SK1\\SSK2",
                          "K2",
                          "K2\\SK1\\SSK1",
                          "K2\\SK1\\SSK2"};
        Departments departments = new Departments(input);
        String[] expect = {"K2",
                           "K2\\SK1",
                           "K2\\SK1\\SSK2",
                           "K2\\SK1\\SSK1",
                           "K1",
                           "K1\\SK2",
                           "K1\\SK1",
                           "K1\\SK1\\SSK2",
                           "K1\\SK1\\SSK1"};
        assertThat(departments.sortDescending().toArray(), is(expect));
    }

    @Test
    public void whenServicesIsNull() {
        String[] input = {"K1",
                          "K2",
                          "K2\\SK1\\SSK1",
                          "K2\\SK1\\SSK2"};
        Departments departments = new Departments(input);
        String[] expect = {"K2",
                           "K2\\SK1",
                           "K2\\SK1\\SSK2",
                           "K2\\SK1\\SSK1",
                           "K1"};
        assertThat(departments.sortDescending().toArray(), is(expect));
    }
}
