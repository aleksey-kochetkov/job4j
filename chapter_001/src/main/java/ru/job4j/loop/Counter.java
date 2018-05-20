package ru.job4j.loop;

/**
 * @author Aleksey Kochetkov
 */
public class Counter {

    /*
     * Вычислить сумму чётных чисел.
     * @param start от
     * @param finish до
     * @return сумма
     */
    public int add(int start, int finish) {
        int result = 0;
        for (int i = start; i <= finish; i++) {
            if (i % 2 == 0) {
                result += i;
            }
        }
        return result;
    }
}
