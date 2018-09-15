package services;

public class StacksBasedQueue<T> {
    private SimpleStack<T> in = new SimpleStack<>();
    private SimpleStack<T> out = new SimpleStack<>();

    public T poll() {
        if (this.out.size() == 0) {
            while (this.in.size() > 0) {
                T t = this.in.pop();
                this.out.push(t);
            }
        }
        return out.pop();
    }

    public void push(T value) {
        this.in.push(value);
    }
}
