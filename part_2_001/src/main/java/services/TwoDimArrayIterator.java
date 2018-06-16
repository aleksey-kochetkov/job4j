package services;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Итератор над двумерным массивом.
 * @author Aleksey Kochetkov
 */
public class TwoDimArrayIterator implements Iterator<Integer> {
    private int[][] data;
    private int row;
    private int col;

    TwoDimArrayIterator(int[][] data) {
        this.data = data;
        this.row = 0;
        this.col = 0;
    }

    @Override
    public boolean hasNext() {
// если первая строка массива имеет нулевую длину, например {{}, {33}},
// то требуется перевести текущие row, col к существующему элементу (row=1 col=0)
        this.checkRowCol();
        return this.row < this.data.length;
    }

    @Override
    public Integer next() {
        if (!this.hasNext()) {
            throw new NoSuchElementException();
        }
        return this.data[this.row][this.col++];
    }

    /**
     * Если row, col не указывают на допустимый индекс массива, то
     * прокрутить их вперёд, пока не найдутся допустимые значения,
     * либо row не выйдет за границу массива.
     */
    private void checkRowCol() {
        while (this.row < this.data.length
// если текущее положение указателя колонки вышло за длину строки,
               && this.data[this.row].length == this.col) {
// тогда перевести текущее положение на следующую строку
            this.row++;
            this.col = 0;
        }
    }
}
