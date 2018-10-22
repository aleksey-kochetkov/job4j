package e;

public interface Storage {
    void createUser(User user);
    int countUsers();
    void close();
}
