package tracker;

/**
 * @author Aleksey Kochetkov
 */
public interface UserAction {
    int key();
    void execute(Input input, Tracker tracker);
    String info();
}
