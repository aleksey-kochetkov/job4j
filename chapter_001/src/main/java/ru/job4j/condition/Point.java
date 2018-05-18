package ru.job4j.condition;

/**
 * Точка
 * @author Aleksey Kochetkov
 */
public class Point {
    private int x;
    private int y;

    Point(int x, int y) {
        this.x = x;
        this.y = y;
    }

    /**
     * Расстояние до другой точки.
     * @param that другая точка
     * @return расстояние
     */
    public double distanceTo(Point that) {
        return Math.sqrt(
                Math.pow(that.x - this.x, 2) + Math.pow(that.y - this.y, 2));
    }

    public static void main(String[] args) {
        Point a = new Point(0, 1);
        Point b = new Point(2, 5);
        double result = a.distanceTo(b);
        System.out.println("Расстояние между точками: " + result);
    }
}
