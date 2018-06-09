package ru.job4j.list;

import java.util.List;
import java.util.Set;
import java.util.TreeSet;
import java.util.Comparator;

public class SortUser {

    /**
     * Сортировка по возрасту.
     * @param list входные данные
     * @return результат
     */
    public Set<User> sort(List<User> list) {
        return new TreeSet<>(list);
    }

    /**
     * Сортировка по длине имени.
     * @param input входные данные
     * @return отсортированные входные данные
     */
    public List<User> sortNameLength(List<User> input) {
        input.sort(new Comparator<User>() {
            @Override
            public int compare(User o1, User o2) {
                return o1.getName().length() - o2.getName().length();
            }
        });
        return input;
    }

    /**
     * Сортировка по имени, возрасту.
     * @param input входные данные
     * @return отсортированные входные данные
     */
    public List<User> sortByAllFields(List<User> input) {
        input.sort(new Comparator<User>() {
            @Override
            public int compare(User o1, User o2) {
                int result = o1.getName().compareTo(o2.getName());
                return result == 0 ? o1.getAge() - o2.getAge() : result;
            }
        });
        return input;
    }
}
