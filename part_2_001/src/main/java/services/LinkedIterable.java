package services;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class LinkedIterable<E> implements Iterable<E> {
    private int size;
    private Node first;
    /**
     * ускоряет выполнение add()
     */
    private Node last;
    private int modCount;

    private class Node {
        E data;
        Node next;

        Node(E data) {
            this.data = data;
        }
    }

    public void add(E value) {
        this.modCount++;
        Node n = new Node(value);
        if (first == null) {
            first = n;
            last = first;
        } else {
            this.last.next = n;
            this.last = n;
        }
        this.size++;
    }

    public E get(int index) {
        Node node = this.first;
        if (index >= this.size || index < 0) {
            throw new IndexOutOfBoundsException();
        }
        for (int i = 0; i < index; i++) {
            node = node.next;
        }
        return node.data;
    }

    public Iterator<E> iterator() {
        return new Iterator<E>() {
            private Node nextNode = LinkedIterable.this.first;
            private int expectedModCount = LinkedIterable.this.modCount;

            @Override
            public boolean hasNext() {
                return nextNode != null;
            }

            @Override
            public E next() {
                if (!this.hasNext()) {
                    throw new NoSuchElementException();
                }
                if (this.expectedModCount != LinkedIterable.this.modCount) {
                    throw new ConcurrentModificationException();
                }
                E result = this.nextNode.data;
                this.nextNode = this.nextNode.next;
                return result;
            }
        };
    }
}