package services;

public class SimpleQueue<E> extends LinkedIterable<E> {

    /**
     * Переименовал, чтоб в голове не путалось.
     * У java.util.Queue парные методы имеют имена add() и remove().
     * @param element элемент
     */
    @Override
    public void add(E element) {
        super.add(element);
    }

    /**
     * Переименовал, чтоб в голове не путалось.
     * У java.util.Queue парные методы имеют имена add() и remove().
     */
    public E remove() {
        return this.remove(0);
    }
}
