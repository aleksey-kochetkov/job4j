package e;

import java.util.List;
import java.util.ArrayList;

public class Storage {
    private List<User> users = new ArrayList<>();

    public void createUser(User user) {
        this.users.add(user);
    }

    public int usersSize() {
        return this.users.size();
    }
}
