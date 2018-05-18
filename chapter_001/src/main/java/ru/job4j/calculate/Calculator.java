package ru.job4j.calculate;

/**
 * Calculator.
 * @author Aleksey Kochetkov
 */
public class Calculator {

    private double result;

    /**
     * Addition.
     * @param first first operand
     * @param second second operand
     */
    public void add(double first, double second) {
        this.result = first + second;
    }

    /**
     * Subtraction.
     * @param first first operand
     * @param second second operand
     */
    public void subtract(double first, double second) {
        this.result = first - second;
    }

    /**
     * Multiplication.
     * @param first first operand
     * @param second second operand
     */
    public void multiply(double first, double second) {
        this.result = first * second;
    }

    /**
     * Division
     * @param first first operand
     * @param second second operand
     */
    public void div(double first, double second) {
        this.result = first / second;
    }

    public double getResult() {
        return this.result;
    }
}
