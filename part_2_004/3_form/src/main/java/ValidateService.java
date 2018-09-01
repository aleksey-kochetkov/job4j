import java.util.List;

public final class ValidateService {
    private static final ValidateService INSTANCE = new ValidateService();
    private final Store store = MemoryStore.getInstance();

    public static ValidateService getInstance() {
        return INSTANCE;
    }

    private ValidateService() {
    }

    public void add(User user) {
        if (user.getName() == null) {
            throw new IllegalArgumentException();
        }
        this.store.add(user);
    }

    public void update(User user) {
        if (this.store.findById(user.getId()) == null
                     || user.getName() == null || user.getLogin() == null
                                            || user.getEmail() == null) {
            throw new IllegalArgumentException();
        }
        this.store.update(user);
    }

    public void delete(User user) {
        if (this.store.findById(user.getId()) == null) {
            throw new IllegalArgumentException();
        }
        this.store.delete(user);
    }

    public List<User> findAll() {
        return this.store.findAll();
    }

    public User findById(int id) {
        User result = this.store.findById(id);
        if (result == null) {
            throw new IllegalArgumentException();
        }
        return result;
    }
}
