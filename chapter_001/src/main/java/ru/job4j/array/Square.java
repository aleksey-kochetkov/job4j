package ru.job4j.array;

/**
 * @author Aleksey Kochetkov
 */
public class Square {
    public int[] calculate(int bound) {
        int[] result = new int[bound];
        for (int i = 0; i < bound; i++) {
            result[i] = (i + 1) * (i + 1);
        }
        return result;
    }
}
