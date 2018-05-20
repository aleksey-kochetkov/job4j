package ru.job4j.condition;

public class Triangle {
    private Point a;
    private Point b;
    private Point c;

    public Triangle(Point a, Point b, Point c) {
        this.a = a;
        this.b = b;
        this.c = c;
    }

    /**
     * Вычисление полупериметра треугольника.
     * @param ab длина стороны ab
     * @param ac длина стороны ac
     * @param bc длина стороны bc
     * @return полупериметр треугольник
     */
    public double period(double ab, double ac, double bc) {
        return (ab + ac + bc) / 2;
    }

    /**
     * Вычисление площади треугольника.
     * Для данной реализации я рассматриваю три точки на одной прямой как
     * вырожденный треугольник с площадью 0. Поэтому в такой реализации
     * не возникнет ситуации когда треугольник не существует.
     * @return площадь треугольника или -1 если треугольник не существует
     */
    public double area() {
      double rsl = -1;
      double ab = this.a.distanceTo(b);
      double ac = this.a.distanceTo(c);
      double bc = this.b.distanceTo(c);
      double p = this.period(ab, ac, bc);

      if (this.exists(ab, ac, bc)) {
          rsl = Math.sqrt(p * (p - ab) * (p - ac) * (p - bc));
      }

      return rsl;
    }

    /**
     * Критерий существования треугольника с заданными длинами сторон.
     * @param ab длина стороны ab
     * @param ac длина стороны ac
     * @param bc длина стороны bc
     * @return треугольник существует
     */
    private boolean exists(double ab, double ac, double bc) {
        double halfperimeter = period(ab, ac, bc);
        return halfperimeter - ab >= 0 && halfperimeter - ac >= 0
                                              && halfperimeter - bc >= 0;
    }
}
