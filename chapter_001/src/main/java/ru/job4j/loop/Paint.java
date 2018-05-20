package ru.job4j.loop;

import java.util.function.BiPredicate;

public class Paint {

    public String pyramid(int height) {
        return loopBy(height, height * 2 - 1,
          (row, column) -> row >= height - column - 1 && row + height - 1 >= column);
    }

    public String rightTrl(int height) {
        return loopBy(height, height, (row, column) -> row >= column);
    }

    public String leftTrl(int height) {
        return loopBy(height, height, (row, column) -> row >= height - column - 1);
    }

    private String loopBy(int height, int width, BiPredicate<Integer, Integer> biPredicate) {
        StringBuilder screen = new StringBuilder();

        for (int row = 0; row != height; row++) {
            for (int column = 0; column != width; column++) {
                if (biPredicate.test(row, column)) {
                    screen.append("^");
                } else {
                    screen.append(" ");
                }
            }
            screen.append(System.lineSeparator());
        }
        return screen.toString();
    }
}
