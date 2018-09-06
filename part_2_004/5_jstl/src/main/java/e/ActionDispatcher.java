package e;

import java.util.Map;
import java.util.HashMap;
import java.util.function.BiConsumer;

public class ActionDispatcher {
    private final Map<String, BiConsumer<User, User>> map = new HashMap<>();

    public void load(String action, BiConsumer<User, User> consumer) {
        this.map.put(action, consumer);
    }

    public void dispatch(String action, User user, User operator) {
        this.map.get(action).accept(user, operator);
    }
}
