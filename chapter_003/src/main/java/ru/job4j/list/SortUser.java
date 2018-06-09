package ru.job4j.list;

import java.util.List;
import java.util.Set;
import java.util.TreeSet;

public class SortUser {
    Set<User> sort(List<User> list) {
        return new TreeSet<>(list);
    }
}
