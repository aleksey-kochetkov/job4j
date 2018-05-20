package ru.job4j.loop;

import static org.junit.Assert.assertThat;
import static org.hamcrest.core.Is.is;
import org.junit.Test;

/**
 * @author Aleksey Kochetkov
 */
public class BoardTest {

    @Test
    public void whenPaintBoardWithWidthTreeAndHeightThreeThenStringWithThreeColsAndThreeRows() {
        Board board = new Board();
        String result = board.paint(3, 3);
        final String LINE = System.getProperty("line.separator");
        String expected = String.format("X X%s X %sX X%s", LINE, LINE, LINE);
        assertThat(result, is(expected));
    }

    @Test
    public void whenPaintBoardWithWidthFiveAndHeightFourThenStringWithFiveColsAndFourRows() {
        Board board = new Board();
        String result = board.paint(5, 4);
        final String LINE = System.getProperty("line.separator");
        String expected = String.format("X X X%s X X %sX X X%s X X %s",
                                                 LINE, LINE, LINE, LINE);
        assertThat(result, is(expected));
    }
}
