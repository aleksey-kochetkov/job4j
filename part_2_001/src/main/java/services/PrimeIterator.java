package services;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Итератор для значений элементов массива являющихся простыми числами.
 * @author Aleksey Kochetkov
 */
public class PrimeIterator implements Iterator<Integer> {
    private int[] numbers;
    private static final PrimalityTest TEST = new PrimalityTest();
    private int next = 0;

    public PrimeIterator(int[] numbers) {
        this.numbers = numbers;
    }

    @Override
    public boolean hasNext() {
        boolean result = false;
        for (int i = this.next; i < this.numbers.length; i++) {
            if (TEST.isPrime(numbers[i])) {
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
