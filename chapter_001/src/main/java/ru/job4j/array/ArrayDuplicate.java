package ru.job4j.array;

import java.util.Arrays;

/**
 * Убрать дубли из массива.
 * Использовать перегруппировку и Arrays.copyOf().
 * @author Aleksey Kochetkov
 */
public class ArrayDuplicate {

    public String[] remove(String[] array) {
        int current = 0;
        int last = array.length - 1;
        while (current < last) {
            for (int i = current + 1; i <= last; i++) {
                if (array[i].equals(array[current])) {
                    if (array[last].equals(array[i])) {
                        last--;
                        i--;
                    } else {
                        this.swap(array, i, last--);
                    }
                }
            }
            current++;
        }
        return Arrays.copyOf(array, last + 1);
    }

    private void swap(String[] array, int a, int b) {
        String tmp = array[a];
        array[a] = array[b];
        array[b] = tmp;
    }
}
