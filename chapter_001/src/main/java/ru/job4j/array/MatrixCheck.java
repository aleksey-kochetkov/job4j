package ru.job4j.array;

/**
 * Проверка диагоналей матрицы.
 * @author Aleksey Kochetkov
 */
public class MatrixCheck {
    public boolean mono(boolean[][] data) {
        boolean result = true;
        for (int y = 0; y < data.length; y++) {
            for (int x = 0; x < data.length; x++) {
                if (x - y < 0) {
                    if (data[y][x] != data[y - x][0]) {
                        result = false;
                        break;
                    }
                } else {
                    if (data[y][x] != data[0][x - y]) {
                        result = false;
                        break;
                    }
                }
            }
        }
        return result;
    }
}
