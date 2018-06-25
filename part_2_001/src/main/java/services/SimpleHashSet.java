package services;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Учебно-тренировочный hash set.
 * По условиям задачи коллизии не обрабатывать!
 * Поэтому в случае коллизии данная реализация работает некорректно с
 * точки зрения здравого смысла! Реализация учебная.
 */
public class SimpleHashSet<E> implements Iterable<E> {
    private Object[] elements = new Object[1];
    private int size = 0;
    private int modCount;

    /**
     * Null не допускается, т.к. имеет внутреннее предназначение.
     * @param element элемент
     * @return операция произведена
     */
    public boolean add(E element) {
        if (element == null) {
            throw new NullPointerException();
        }
        boolean result = false;
        if (this.size == this.elements.length) {
            this.resize();
        }
        if (!this.contains(element)) {
            this.modCount++;
            this.elements[this.hash(element)] = element;
            this.size++;
            result = true;
        }
        return result;
    }

    public boolean contains(E element) {
        return this.elements[this.hash(element)] != null;
    }

    public boolean remove(E element) {
        boolean result = this.contains(element);
        if (result) {
            this.modCount++;
            this.elements[this.hash(element)] = null;
            size--;
        }
        return result;
    }

    @Override
    public Iterator<E> iterator() {
        return new Iterator<E>() {
            private int next;
            private int expectedModCount = SimpleHashSet.this.modCount;

            @Override
            public boolean hasNext() {
                return this.next < SimpleHashSet.this.size;
            }

            @SuppressWarnings("unchecked")
            @Override
            public E next() {
                if (this.expectedModCount != SimpleHashSet.this.modCount) {
                    throw new ConcurrentModificationException();
                }
                if (!this.hasNext()) {
                    throw new NoSuchElementException();
                }
                return (E) SimpleHashSet.this.elements[next++];
            }
        };
    }

    private int hash(Object element) {
        return element.hashCode() & (this.elements.length - 1);
    }

    private void resize() {
        Object[] oldElements = this.elements;
        this.elements = new Object[this.elements.length * 2];
        for (Object e : oldElements) {
            this.elements[hash(e)] = e;
        }
    }
}
