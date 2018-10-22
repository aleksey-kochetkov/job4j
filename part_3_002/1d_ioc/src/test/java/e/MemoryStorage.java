package e;

import java.util.List;
import java.util.ArrayList;

public class MemoryStorage implements Storage {
    private List<User> users = new ArrayList<>();

    @Override
    public void createUser(User user) {
        this.users.add(user);
    }

    @Override
    public int countUsers() {
        return users.size();
    }

    @Override
    public void close() {
    }
}
