package services;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Итератор для чётных значений элементов массива.
 * @author Aleksey Kochetkov
 */
public class EvenIterator implements Iterator<Integer> {
    private int[] numbers;
    private int next = 0;

    public EvenIterator(int[] numbers) {
        this.numbers = numbers;
    }

    @Override
    public boolean hasNext() {
        boolean result = false;
        for (int i = this.next; i < this.numbers.length; i++) {
            if (this.numbers[i] % 2 == 0) {
                this.next = i;
                result = true;
                break;
            }
        }
        return result;
    }

    @Override
    public Integer next() {
        if (!this.hasNext()) {
            throw new NoSuchElementException();
        }
        return this.numbers[this.next++];
    }
}
