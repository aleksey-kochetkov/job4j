package ru.job4j.list;

import org.junit.Test;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;
import static org.junit.Assert.assertThat;
import static org.hamcrest.core.Is.is;

public class UserConvertTest {
    @Test
    public void when() {
        User zero = new User("Андрей", "Санкт-Петербург");
        User one = new User("Пьер", "Москва");
        User two = new User("Наташа", "Жмеринка");
        List<User> input = new ArrayList<>();
        input.add(two);
        input.add(zero);
        input.add(one);
        Map<Integer, User> expect = new HashMap<>();
        expect.put(0, zero);
        expect.put(1, one);
        expect.put(2, two);
        assertThat(new UserConvert().process(input), is(expect));
    }
}
