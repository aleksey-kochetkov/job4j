package ru.job4j.chess.figures.white;

import ru.job4j.chess.figures.Cell;
import ru.job4j.chess.figures.Figure;
import ru.job4j.chess.exception.ImpossibleMoveException;

/**
 *
 * @author Petr Arsentev (parsentev@yandex.ru)
 * @version $Id$
 * @since 0.1
 */
public class QeenWhite implements Figure {
    private final Cell position;

    public QeenWhite(final Cell position) {
        this.position = position;
    }

    @Override
    public Cell position() {
        return this.position;
    }

    @Override
    public Cell[] way(Cell source, Cell dest) throws ImpossibleMoveException {
        if (Math.abs(dest.y - source.y) != Math.abs(dest.x - source.x)
            && dest.y != source.y && dest.x != source.x) {
            throw new ImpossibleMoveException();
        }
        int numSteps = 0;
        int dx = 0;
        if (dest.x != source.x) {
            numSteps = Math.abs(dest.x - source.x);
            dx = dest.x > source.x ? 1 : -1;
        }
        int dy = 0;
        if (dest.y != source.y) {
            numSteps = Math.abs(dest.y - source.y);
            dy = dest.y > source.y ? 1 : -1;
        }
        Cell[] steps = new Cell[numSteps];
        int x = source.x;
        int y = source.y;
        for (int i = 0; i < numSteps; i++) {
            x += dx;
            y += dy;
            steps[i] = Cell.valueOf(x, y);
        }
        return steps;
    }

    @Override
    public Figure copy(Cell dest) {
        return new QeenWhite(dest);
    }
}
