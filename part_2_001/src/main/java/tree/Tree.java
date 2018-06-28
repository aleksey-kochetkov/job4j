package tree;

import java.util.Optional;

public interface Tree<E extends Comparable<E>> extends Iterable<E> {

    /**
     * Добавить элемент childValue в parentValue.
     * Parent может иметь список child.
     * @param parentValue value of parent node.
     * @param childValue value of child node.
     * @return was added
     */
    boolean add(E parentValue, E childValue);

    Optional<Node<E>> findBy(E value);
}