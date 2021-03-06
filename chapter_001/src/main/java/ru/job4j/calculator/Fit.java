package ru.job4j.calculator;

/**
 * Расчёт идеального веса.
 */
public class Fit {

    /**
     * Идеальный вес для мужчины.
     * @param height рост
     * @return идеальный вес
     */
    public double manWeight(double height) {
        return (height - 100) * 1.15;
    }

    /**
     * Идеальный вес для женщины.
     * @param height рост
     * @return идеальный вес
     */
    public double womanWeigth(double height) {
        return (height - 110) * 1.15;
    }
}
