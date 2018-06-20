package services;

public class SimpleStack<E> extends LinkedIterable<E> {

    public void push(E element) {
        this.add(element);
    }

    public E pop() {
        return this.remove(this.size() - 1);
    }
}
