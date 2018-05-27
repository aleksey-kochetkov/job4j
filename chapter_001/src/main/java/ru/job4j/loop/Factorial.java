package ru.job4j.loop;

/**
 * Вычисление факториала
 * @author Aleksey Kochetkov
 */
public class Factorial {

    /** Я не понимаю, как можно обойтись без обработки отрицательных
     * значений аргумента. На них факториал не существует. Но раз
     * реализация с IllegalArgumentException не устроила, то сделаю
     * возврат -1.
     */
    public int calc(int n) {
        int result;
        if (n < 0) {
            result = -1;
        } else {
            result = 1;
        }
        while (n > 0) {
            result *= n--;
        }
        return result;
    }
}
