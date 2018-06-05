package ru.job4j.chess.figures;

import ru.job4j.chess.exception.ImpossibleMoveException;

public interface Figure {
    Cell position();

    Cell[] way(Cell source, Cell dest) throws ImpossibleMoveException;

    default String icon() {
        return String.format(
                "%s.png", this.getClass().getSimpleName()
        );

    }

    Figure copy(Cell dest);

}
