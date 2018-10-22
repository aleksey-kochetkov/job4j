package e;

import org.springframework.stereotype.Component;

public class UserStorage {
    Storage storage;

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
