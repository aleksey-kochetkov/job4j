package services;

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.ConcurrentModificationException;

/**
 * Реализация Set на основе связанного списка.
 * @param <E>
 */
public class LinkedSet<E> implements Iterable<E> {
    private Node first;
    private int modCount;

    private class Node {
        E data;
        Node next;

        Node(E data) {
            this.data = data;
        }
    }

    private Node createNode(E element) {
        Node node = new Node(element);
        this.modCount++;
        return node;
    }

    public void add(E element) {
        if (this.first == null) {
            this.first = this.createNode(element);
        } else {
            boolean exists = false;
            Node node = null;
            do {
                node = node == null ? this.first : node.next;
                if (node.data == null && element == null
                    || node.data != null && node.data.equals(element)) {
                    exists = true;
                    break;
                }
            } while (node.next != null);
            if (!exists) {
                node.next = this.createNode(element);
            }
        }
    }

    @Override
    public Iterator<E> iterator() {
        return new Iterator<E>() {
            private Node nextNode = LinkedSet.this.first;
            private int expectedModCount = LinkedSet.this.modCount;

            @Override
            public boolean hasNext() {
                return nextNode != null;
            }

            @Override
            public E next() {
                if (!this.hasNext()) {
                    throw new NoSuchElementException();
                }
                if (this.expectedModCount != LinkedSet.this.modCount) {
                    throw new ConcurrentModificationException();
                }
                E result = this.nextNode.data;
                this.nextNode = this.nextNode.next;
                return result;
            }
        };
    }
}
