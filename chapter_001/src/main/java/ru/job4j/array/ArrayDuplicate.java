package ru.job4j.array;

import java.util.Arrays;

/**
 * Убрать дубли из массива.
 * Использовать перегруппировку и Arrays.copyOf().
 * @author Aleksey Kochetkov
 */
public class ArrayDuplicate {

    public String[] remove(String[] array) {
        int last = array.length - 1;
        for (int current = 0; current < last; current++) {
            for (int i = current + 1; i <= last; i++) {
                if (array[i].equals(array[current])) {
                    String tmp = array[i];
                    array[i--] = array[last];
                    array[last--] = tmp;
                }
            }
        }
        return Arrays.copyOf(array, last + 1);
    }
}
