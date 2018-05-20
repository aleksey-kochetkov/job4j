package ru.job4j.loop;

import com.sun.javaws.exceptions.InvalidArgumentException;

/**
 * Вычисление факториала
 * @author Aleksey Kochetkov
 */
public class Factorial {
    public int calc(int n) {
        if (n < 0) {
            throw new IllegalArgumentException(
                    String.format("%d < 0", n));
        }
        if (n == 0) {
            return 1;
        }

        return calc(n - 1) * n;
    }
}
