package services;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Итератор для чётных значений элементов массива.
 * @author Aleksey Kochetkov
 */
public class EvenIterator implements Iterator<Integer> {
    private int[] numbers;
    /**
     * Текущий элемент итератора.
     * -1 если ещё не вычислен.
     */
    private int current = -1;
    /**
     * Следующий элемент итератора.
     * -1 если ещё не вычислен.
     */
    private int next = -1;

    public EvenIterator(int[] numbers) {
        this.numbers = numbers;
    }

    @Override
    public boolean hasNext() {
        boolean result = next != -1;
        if (!result) {
            for (int i = current + 1; i < numbers.length; i++) {
                if (this.numbers[i] % 2 == 0) {
                    this.next = i;
                    result = true;
                    break;
                }
            }
        }
        return result;
    }

    @Override
    public Integer next() {
        if (!this.hasNext()) {
            throw new NoSuchElementException();
        }
        this.current = this.next;
        this.next = -1;
        return this.numbers[this.current];
    }
}
