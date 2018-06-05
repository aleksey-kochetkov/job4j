package ru.job4j;

import ru.job4j.chess.figures.Cell;
import ru.job4j.chess.figures.Figure;
import ru.job4j.chess.figures.white.BishopWhite;
import ru.job4j.chess.exception.ImpossibleMoveException;
import org.junit.Test;
import static org.junit.Assert.fail;
import static org.junit.Assert.assertThat;
import static org.hamcrest.core.Is.is;

public class BishopWhiteTest {

    @Test
    public void whenC1H6() {
        Figure bishop = new BishopWhite(Cell.C1);
        Cell[] result = null;
        try {
            result = bishop.way(bishop.position(), Cell.H6);
        } catch (ImpossibleMoveException exception) {
            fail();
        }
        Cell[] expect = new Cell[] {Cell.D2, Cell.E3, Cell.F4, Cell.G5, Cell.H6};
        assertThat(result, is(expect));
    }

    @Test(expected = ImpossibleMoveException.class)
    public void whenC1C2ThenImpossibleMoveException() throws ImpossibleMoveException {
        Figure bishop = new BishopWhite(Cell.C1);
        bishop.way(bishop.position(), Cell.C6);
    }
}
