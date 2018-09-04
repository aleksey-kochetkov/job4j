package e;

import java.util.List;
import java.util.ArrayList;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.atomic.AtomicInteger;

public final class MemoryStore implements Store {
    private static final MemoryStore INSTANCE = new MemoryStore();
    private static final AtomicInteger NEXT_ID = new AtomicInteger();
    private final List<User> store = new CopyOnWriteArrayList<>();

    public static MemoryStore getInstance() {
        return INSTANCE;
    }

    private MemoryStore() {
    }

    @Override
    public void add(User user) {
        user.setId(NEXT_ID.getAndIncrement());
        this.store.add(user);
    }

    @Override
    public void update(User user) {
        User found = this.store.get(this.getIndex(user.getId()));
        found.setName(user.getName());
        found.setLogin(user.getLogin());
        found.setEmail(user.getEmail());
    }

    @Override
    public void delete(User user) {
        this.store.remove(this.getIndex(user.getId()));
    }

    @Override
    public List<User> findAll() {
        return new ArrayList<>(this.store);
    }

    @Override
    public User findById(int id) {
        return this.store.get(this.getIndex(id));
    }

    private int getIndex(int id) {
        int result = -1;
        for (int i = 0; i < this.store.size(); i++) {
            if (this.store.get(i).getId() == id) {
                result = i;
                break;
            }
        }
        return result;
    }
}
