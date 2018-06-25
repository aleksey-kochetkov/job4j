package services;

import java.util.Iterator;

/**
 * Реализация Set на основе предыдущего задания "список на массиве".
 */
public class ArraySet<E> implements Iterable<E> {
    private ArrayIterable<E> data = new ArrayIterable<>();

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
