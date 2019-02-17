package service;

import java.io.InputStream;
import java.io.IOException;

/**
 * Реализовать сервис:
 * Метод должен проверить, что в байтовом потоке записано четное число.
 * (Такое имя метода задано в задании!)
 */
public class NumService {
    public boolean isNumber(InputStream in) throws IOException {
        return in.read() % 2 == 0;
    }
}
