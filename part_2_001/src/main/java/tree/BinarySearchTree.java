package tree;

import java.util.Iterator;
import java.util.Deque;
import java.util.LinkedList;
import java.util.NoSuchElementException;

public class BinarySearchTree<E extends Comparable<E>> implements Iterable<E> {

    private class Node {
        Node left;
        Node right;
        E element;

        Node(E element) {
            this.element = element;
        }
    }

    private Node root;

    public void add(E element) {
        this.root = add(this.root, element);
    }

    private Node add(Node parent, E element) {
        Node result;
        if (parent == null) {
            result = new Node(element);
        } else {
            result = parent;
            if (parent.element.compareTo(element) >= 0) {
                parent.left = add(parent.left, element);
            } else {
                parent.right = add(parent.right, element);
            }
        }
        return result;
    }

    /**
     * Итератор для дерева в порядке сортировки заложенном в дереве.
     * @return итератор
     */
    public Iterator<E> iterator() {
        return new Iterator<E>() {
            private Deque<Node> stack;

            @Override
            public boolean hasNext() {
                if (this.stack == null && BinarySearchTree.this.root != null) {
                    this.stack = new LinkedList<>();
                    this.offerLeftBranch(BinarySearchTree.this.root);
                }
                return this.stack != null && this.stack.size() > 0;
            }

            @Override
            public E next() {
                if (!this.hasNext()) {
                    throw new NoSuchElementException();
                }
                Node node = this.stack.pollFirst();
                this.offerLeftBranch(node.right);
                return node.element;
            }

            private void offerLeftBranch(Node node) {
                while (node != null) {
                    this.stack.offerFirst(node);
                    node = node.left;
                }
            }
        };
    }
}
