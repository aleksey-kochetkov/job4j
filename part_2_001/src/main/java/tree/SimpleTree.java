package tree;

import java.util.Iterator;
import java.util.Optional;
import java.util.Queue;
import java.util.Deque;
import java.util.LinkedList;
import java.util.NoSuchElementException;
import java.util.ConcurrentModificationException;

public class SimpleTree<E extends Comparable<E>> implements Tree<E> {
    private Node<E> root;
    private int modCount;

    public SimpleTree(E rootValue) {
        this.root = new Node<>(rootValue);
    }

    /**
     * Добавить элемент childValue в parentValue.
     * Parent может иметь список child.
     * @param parentValue value of parent node.
     * @param childValue value of child node.
     * @return was added
     */
    @Override
    public boolean add(E parentValue, E childValue) {
        boolean result = false;
        if (!this.findBy(childValue).isPresent()) {
            Optional<Node<E>> parent = findBy(parentValue);
            if (parent.isPresent()) {
                modCount++;
                result = true;
                parent.get().add(new Node<>(childValue));
            }
        }
        return result;
    }

    @Override
    public Optional<Node<E>> findBy(E value) {
        Optional<Node<E>> rsl = Optional.empty();
        Queue<Node<E>> data = new LinkedList<>();
        data.offer(this.root);
        while (!data.isEmpty()) {
            Node<E> el = data.poll();
            if (el.eqValue(value)) {
                rsl = Optional.of(el);
                break;
            }
            for (Node<E> child : el.leaves()) {
                data.offer(child);
            }
        }
        return rsl;
    }

    @Override
    public Iterator<E> iterator() {
        return new Iterator<E>() {
            private int expectedModCount = SimpleTree.this.modCount;
            private Deque<Iterator<Node<E>>> iterators;

            @Override
            public boolean hasNext() {
                boolean result = false;
                if (this.iterators == null) {
                    if (SimpleTree.this.root != null) {
                        result = true;
                    }
                } else {
                    do {
                        result = true;
                        if (!this.iterators.peekFirst().hasNext()) {
                            this.iterators.pollFirst();
                            result = false;
                        }
                    } while (!result && !this.iterators.isEmpty());
                }
                return result;
            }

            @Override
            public E next() {
                if (this.expectedModCount != SimpleTree.this.modCount) {
                    throw new ConcurrentModificationException();
                }
                if (!this.hasNext()) {
                    throw new NoSuchElementException();
                }
                E result;
                if (this.iterators == null) {
                    this.iterators = new LinkedList<>();
                    result = this.offerIterator(SimpleTree.this.root);
                } else {
                    result = this.offerIterator(this.iterators.peekFirst().next());
                }
                return result;
            }

            private E offerIterator(Node<E> node) {
                if (!node.leaves().isEmpty()) {
                    this.iterators.offerFirst(node.leaves().iterator());
                }
                return node.getValue();
            }
        };
    }
}