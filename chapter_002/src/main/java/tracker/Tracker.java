package tracker;

import java.util.Arrays;
import java.util.List;
import java.util.ArrayList;
import java.util.Random;

/**
 * Система ведения заявок.
 * @author Aleksey Kochetkov
 */
public class Tracker {
    private static final Random RANDOM = new Random();
    private List<Item> items = new ArrayList<>();

    public Item add(Item item) {
        item.setId(this.generateId());
        items.add(item);
        return item;
    }

    public void replace(String id, Item item) {
        for (int i = 0; i < this.items.size(); i++) {
            if (this.items.get(i).getId().equals(id)) {
                item.setId(this.items.get(i).getId());
                this.items.set(i, item);
            }
        }
    }

    public void delete(String id) {
        for (int i = 0; i < this.items.size(); i++) {
            if (this.items.get(i).getId().equals(id)) {
                this.items.remove(i);
                break;
            }
        }
    }

    public List<Item> getAll() {
        return items;
    }

    public Item findById(String id) {
        Item result = null;
        for (Item i : this.items) {
            if (i.getId().equals(id)) {
                result = i;
                break;
            }
        }
        return result;
    }

    public List<Item> findByName(String name) {
        List<Item> items = new ArrayList<>();
        for (Item i : this.items) {
            if (i.getName().equals(name)) {
                items.add(i);
            }
        }
        return items;
    }

    private String generateId() {
        return String.valueOf(System.currentTimeMillis() + RANDOM.nextInt());
    }
}
