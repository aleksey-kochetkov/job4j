package e;

import java.util.List;
import java.util.ArrayList;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Predicate;

public final class MemoryStore implements Store {
    private static final AtomicInteger NEXT_ID = new AtomicInteger();
    private static final MemoryStore INSTANCE = new MemoryStore().init();
    private final List<User> users = new CopyOnWriteArrayList<>();
    private final List<Role> roles = new CopyOnWriteArrayList<>();

    public static MemoryStore getInstance() {
        return INSTANCE;
    }

    private MemoryStore() {
    }

    private MemoryStore init() {
        Role rootRole = new Role("root", "Администратор");
        this.addRole(rootRole);
        this.addRole(new Role("user", "Пользователь"));
        this.addUser(new User(
                    -1, "Руфь", "root", "ruth@ya.ru", "ruth", rootRole));
        return this;
    }

    @Override
    public void addUser(User user) {
        user.setId(NEXT_ID.getAndIncrement());
        this.users.add(user);
    }

    @Override
    public void updateUser(User user) {
        User found = this.users.get(this.getUserIndex(user.getId()));
        found.setName(user.getName());
        found.setLogin(user.getLogin());
        found.setEmail(user.getEmail());
        found.setPassword(user.getPassword());
        found.setRole(user.getRole());
    }

    @Override
    public void deleteUser(User user) {
        this.users.remove(this.getUserIndex(user.getId()));
    }

    @Override
    public List<User> findAllUsers() {
        return new ArrayList<>(this.users);
    }

    @Override
    public User findUserById(int id) {
        return this.users.get(this.getUserIndex(id));
    }

    @Override
    public User findUserByLogin(String login) {
        User result = null;
        int index = this.getUserIndex(u -> u.getLogin().equals(login));
        if (index >= 0) {
            result = this.users.get(index);
        }
        return result;
    }

    private int getUserIndex(Predicate<User> predicate) {
        int result = -1;
        for (int i = 0; i < this.users.size(); i++) {
            if (predicate.test(this.users.get(i))) {
                result = i;
                break;
            }
        }
        return result;
    }

    private int getUserIndex(int id) {
        return this.getUserIndex(u -> u.getId() == id);
    }


    @Override
    public void addRole(Role role) {
        this.roles.add(role);
    }

    @Override
    public List<Role> findAllRoles() {
        return new ArrayList<>(this.roles);
    }

    @Override
    public Role findRoleByCode(String code) {
        Role result = null;
        int index = this.getRoleIndex(r -> r.getCode().equals(code));
        if (index >= 0) {
            result = this.roles.get(index);
        }
        return result;
    }

    private int getRoleIndex(Predicate<Role> predicate) {
        int result = -1;
        for (int i = 0; i < this.roles.size(); i++) {
            if (predicate.test(this.roles.get(i))) {
                result = i;
                break;
            }
        }
        return result;
    }
}
