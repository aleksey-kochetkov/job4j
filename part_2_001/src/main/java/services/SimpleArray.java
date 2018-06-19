package services;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class SimpleArray<T> implements Iterable<T> {
    private Object[] data;
    private int size = 0;

    public SimpleArray(int length) {
        this.data = new Object[length];
    }

    /**
     * Перехват исключения для того, чтобы все методы класса выбрасывали
     * исключение одного типа (как ArrayList).
     * @param model элемент данных
     */
    public void add(T model) {
        try {
            this.data[size++] = model;
        } catch (ArrayIndexOutOfBoundsException exception) {
            throw new IndexOutOfBoundsException();
        }
    }

    public void set(int index, T model) {
        this.checkIndex(index);
        this.data[index] = model;
    }

    /**
     * Переименовал delete() из задания для единообразия.
     * @param index индекс элемента
     */
    public void remove(int index) {
        checkIndex(index);
        System.arraycopy(this.data, index + 1, this.data, index, this.size - (index + 1));
        this.data[this.size - 1] = null;
        this.size--;
    }

    public T get(int index) {
        checkIndex(index);
        return (T) this.data[index];
    }

    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {
            private int next = 0;

            @Override
            public boolean hasNext() {
                return this.next < SimpleArray.this.size;
            }

            @Override
            public T next() {
                if (!this.hasNext()) {
                    throw new NoSuchElementException();
                }
                return (T) SimpleArray.this.data[this.next++];
            }
        };
    }

    private void checkIndex(int index) {
        if (index >= this.size || index < 0) {
            throw new IndexOutOfBoundsException();
        }
    }
}
