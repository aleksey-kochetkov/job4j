package e;

import org.springframework.stereotype.Component;

@Component
public class UserStorage {
    private final Storage storage;

    public UserStorage(Storage storage) {
        this.storage = storage;
    }

    public void create(User user) {
        this.storage.createUser(user);
    }

    public int size() {
        return storage.usersSize();
    }
}
