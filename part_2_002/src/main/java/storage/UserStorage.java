package storage;

import net.jcip.annotations.GuardedBy;
import net.jcip.annotations.ThreadSafe;

import java.util.Map;
import java.util.HashMap;

@ThreadSafe
public class UserStorage {
    @GuardedBy("this")
    private Map<Integer, User> users = new HashMap<>();

    public synchronized int size() {
        return users.size();
    }

    public synchronized boolean add(User user) {
        return this.users.putIfAbsent(user.getId(), user) == null;
    }

    public synchronized boolean update(User user) {
        this.users.put(user.getId(), user);
        return true;
    }

    public synchronized boolean delete(User user) {
        boolean result = this.users.containsKey(user.getId());
        this.users.remove(user.getId());
        return result;
    }

    public synchronized void transfer(int fromId, int toId, int amount) {
        User fromUser = this.users.get(fromId);
        User toUser = this.users.get(toId);
        fromUser.increaseAmountBy(-amount);
        toUser.increaseAmountBy(amount);
    }

    public synchronized User findById(int id) {
        return this.users.get(id);
    }
}
