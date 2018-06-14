package services;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Структура данных двумерный массив.
 * @author Aleksey Kochetkov
 */
public class TwoDimArrayIterable implements Iterable<Integer> {
    private int[][] data;

    private class TwoDimArrayIterator implements Iterator<Integer> {
        private int row;
        private int col;

        TwoDimArrayIterator() {
            this.row = 0;
            this.col = 0;
            this.checkRowCol();
        }

        @Override
        public boolean hasNext() {
            return this.row < TwoDimArrayIterable.this.data.length;
        }

        @Override
        public Integer next() {
            if (!this.hasNext()) {
                throw new NoSuchElementException();
            }
            int result =
                     TwoDimArrayIterable.this.data[this.row][this.col++];
            this.checkRowCol();
            return result;
        }

        /**
         * Если row, col не указывают на допустимый индекс массива, то
         * прокрутить их вперёд, пока не найдутся допустимые значения,
         * либо row не выйдет за границу массива.
         */
        private void checkRowCol() {
            while (this.row < TwoDimArrayIterable.this.data.length
                                         && TwoDimArrayIterable.this.data
                                         [this.row].length == this.col) {
                this.row++;
                this.col = 0;
            }
        }
    }

    public TwoDimArrayIterable(int[][] data) {
        this.data = data;
    }

    @Override
    public Iterator<Integer> iterator() {
        return new TwoDimArrayIterator();
    }
}
