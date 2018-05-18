package ru.job4j.converter;

/**
 * Конвертор валюты.
 * @author Aleksey Kochetkov
 */
public class Converter {

    /**
     * Конвертация рублей в евро.
     * @param value рубли
     * @return евро
     */
    public int rubleToEuro(int value) {
        return value / 70;
    }

    /**
     * Конвертация рублей в доллары.
     * @param value рубли
     * @return доллары
     */
    public int rubleToDollar(int value) {
        return value / 60;
    }

    /**
     * Конвертация евро в рубли.
     * @param ruble рубли
     * @return euro евро
     */
    public double euroToRuble(double ruble) {
        return ruble * 70;
    }

    public double dollarToRuble(double dollar) {
        return dollar * 60;
    }
}
