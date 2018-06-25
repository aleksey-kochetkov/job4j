package services;

import java.util.Iterator;

/**
 * Реализация Set на основе предыдущего задания.
 */
public class LinkedSet<E> implements Iterable<E> {
    private LinkedIterable<E> data = new LinkedIterable<>();

    public void add(E value) {
        boolean exists = false;
        for (E e : this.data) {
            if (e == null && value == null
                || e != null && e.equals(value)) {
                exists = true;
                break;
            }
        }
        if (!exists) {
            this.data.add(value);
        }
    }

    @Override
    public Iterator<E> iterator() {
        return this.data.iterator();
    }
}
