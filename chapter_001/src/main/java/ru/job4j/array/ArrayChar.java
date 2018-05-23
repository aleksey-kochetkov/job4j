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
        char[] value = prefix.toCharArray();
        boolean result = value.length <= this.data.length;
        if (result) {
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
