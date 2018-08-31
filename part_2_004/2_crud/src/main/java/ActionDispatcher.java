import java.util.Map;
import java.util.HashMap;
import java.util.function.Consumer;

public class ActionDispatcher {
    private final Map<String, Consumer<User>> map = new HashMap<>();

    public void load(String action, Consumer<User> consumer) {
        this.map.put(action, consumer);
    }

    public void dispatch(String action, User user) {
        this.map.get(action).accept(user);
    }
}
