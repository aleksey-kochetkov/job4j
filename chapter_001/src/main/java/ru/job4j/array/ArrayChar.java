package ru.job4j.array;

/**
 * Обёртка над строкой.
 */
public class ArrayChar {
    private char[] data;

    ArrayChar(String line) {
        this.data = line.toCharArray();
    }

    /**
     * Проверяет что слово начинается с префикса.
     * @param prefix префикс
     * @return слово начинается с префикса
     */
    public boolean startsWith(String prefix) {
        boolean result = true;
        char[] value = prefix.toCharArray();
        if (value.length > this.data.length) {
            result = false;
        } else {
            for (int i = 0; i < value.length; i++) {
                if (value[i] != this.data[i]) {
                    result = false;
                    break;
                }
            }
        }
        return result;
    }
}
