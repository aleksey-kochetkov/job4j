package ru.job4j.tictactoe;

import java.util.function.Predicate;

public class Logic3T {
    private final Figure3T[][] table;

    public Logic3T(Figure3T[][] table) {
        this.table = table;
    }

    public boolean isWinnerX() {
        boolean result = isWinnerHorizontalOrVertical(Figure3T::hasMarkX);
        if (!result) {
            result = isWinnerDiagonal(Figure3T::hasMarkX);
        }
        return result;
    }

    public boolean isWinnerO() {
        boolean result = isWinnerHorizontalOrVertical(Figure3T::hasMarkO);
        if (!result) {
            result = isWinnerDiagonal(Figure3T::hasMarkO);
        }
        return result;
    }

    public boolean hasGap() {
        boolean result = false;
        for (int i = 0; i < table.length; i++) {
            for (int j = 0; j < table.length; j++) {
                if (!table[i][j].hasMarkX() && !table[i][j].hasMarkO()) {
                    result = true;
                    break;
                }
            }
        }
        return result;
    }

    private boolean isWinnerHorizontalOrVertical(Predicate<Figure3T> predicate) {
        boolean result = false;
        for (int a = 0; a < table.length; a++) {
            boolean horizontal = true;
            boolean vertical = true;
            for (int b = 0; b < table.length; b++) {
                if (!predicate.test(table[a][b])) {
                    horizontal = false;
                }
                if (!predicate.test(table[b][a])) {
                    vertical = false;
                }
            }
            if (horizontal || vertical) {
                result = true;
                break;
            }
        }
        return result;
    }

    private boolean isWinnerDiagonal(Predicate<Figure3T> predicate) {
        boolean straightDiagonal = true;
        boolean reverseDiagonal = true;
        for (int i = 0; i < table.length; i++) {
            if (!predicate.test(table[i][i])) {
                straightDiagonal = false;
            }
            if (!predicate.test(table[table.length - 1 - i][i])) {
                reverseDiagonal = false;
            }
        }
        return straightDiagonal || reverseDiagonal;
    }
}