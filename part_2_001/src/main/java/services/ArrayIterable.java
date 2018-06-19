package services;

import java.util.Arrays;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.ConcurrentModificationException;

/**
 * Структура данных на основе массива с динамическим расширением.
 * @param <E>
 */
public class ArrayIterable<E> implements Iterable<E> {
    private static final int INITIAL_LENGTH = 10;
    private Object[] container = new Object[INITIAL_LENGTH];
    private int size = 0;
    private int modCount = 0;

    public void add(E value) {
        if (size == container.length) {
            int length = container.length + (container.length >> 1);
            this.container = Arrays.copyOf(this.container, length);
        }
        this.modCount++;
        this.container[size++] = value;
    }

    @SuppressWarnings("unchecked")
    public E get(int index) {
        if (index >= size) {
            throw new IndexOutOfBoundsException();
        }
        return (E) this.container[index];
    }

    @Override
    public Iterator<E> iterator() {
        return new Iterator<E>() {

            private int next = 0;
            private int expectedModCount = ArrayIterable.this.modCount;

            @Override
            public boolean hasNext() {
                return this.next < ArrayIterable.this.size;
            }

            @SuppressWarnings("unchecked")
            @Override
            public E next() {
                if (!this.hasNext()) {
                    throw new NoSuchElementException();
                }
                if (this.expectedModCount != ArrayIterable.this.modCount) {
                    throw new ConcurrentModificationException();
                }
                return (E) ArrayIterable.this.container[this.next++];
            }
        };
    }
}
