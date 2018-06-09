package ru.job4j.list;

import org.junit.Test;
import java.util.List;
import java.util.Set;
import java.util.Arrays;
import java.util.Iterator;
import static org.junit.Assert.assertThat;
import static org.hamcrest.core.Is.is;

public class SortUserTest {
    @Test
    public void whenCallSort() {
        User one = new User("Ниф-ниф", 1);
        User two = new User("Наф-наф", 2);
        User three = new User("Нуф-нуф", 3);
        List<User> input = Arrays.asList(three, one, two);
        SortUser sorter = new SortUser();
        Set<User> result = sorter.sort(input);
        Iterator<User> iterator = result.iterator();
        assertThat(iterator.next().getName(), is("Ниф-ниф"));
        assertThat(iterator.next().getName(), is("Наф-наф"));
        assertThat(iterator.next().getName(), is("Нуф-нуф"));
    }
}
