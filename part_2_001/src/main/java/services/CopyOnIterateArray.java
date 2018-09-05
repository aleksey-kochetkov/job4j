package services;

import net.jcip.annotations.ThreadSafe;
import net.jcip.annotations.GuardedBy;

import java.util.Iterator;

@ThreadSafe
public class CopyOnIterateArray<E> implements Iterable<E> {
    @GuardedBy("this")
    private ArrayIterable<E> array = new ArrayIterable<>();

    public synchronized void add(E e) {
        this.array.add(e);
    }

    public synchronized E get(int index) {
        return this.array.get(index);
    }

    @Override
    public synchronized Iterator<E> iterator() {
        return this.copy(this.array).iterator();
    }

    private ArrayIterable<E> copy(ArrayIterable<E> source) {
        ArrayIterable<E> snapshot = new ArrayIterable<>();
        for (E e : source) {
            snapshot.add(e);
        }
        return snapshot;
    }
}
