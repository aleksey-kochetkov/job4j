package services;

import net.jcip.annotations.ThreadSafe;
import net.jcip.annotations.GuardedBy;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

@ThreadSafe
public class LinkedIterable<E> implements Iterable<E> {
    @GuardedBy("this")
    private int size;
    @GuardedBy("this")
    private Node first;
    /**
     * ускоряет выполнение add()
     */
    @GuardedBy("this")
    private Node last;
    @GuardedBy("this")
    private int modCount;

    private class Node {
        E data;
        Node next;

        Node(E data) {
            this.data = data;
        }
    }

    public synchronized int size() {
        return this.size;
    }

    public synchronized void add(E value) {
        this.modCount++;
        Node node = new Node(value);
        if (first == null) {
            first = node;
            last = first;
        } else {
            this.last.next = node;
            this.last = node;
        }
        this.size++;
    }

    public synchronized E remove(int index) {
        E result;
        this.checkIndex(index);
        if (index == 0) {
            result = this.first.data;
            this.first = this.first.next;
        } else {
            Node previous = this.first;
            for (int i = 1; i < index; i++) {
                previous = previous.next;
            }
            result = previous.next.data;
            previous.next = previous.next.next;
        }
        this.size--;
        return result;
    }

    public synchronized E get(int index) {
        this.checkIndex(index);
        Node node = this.first;
        for (int i = 0; i < index; i++) {
            node = node.next;
        }
        return node.data;
    }

    public synchronized Iterator<E> iterator() {
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

    private synchronized void checkIndex(int index) {
        if (index >= this.size || index < 0) {
            throw new IndexOutOfBoundsException();
        }
    }
}