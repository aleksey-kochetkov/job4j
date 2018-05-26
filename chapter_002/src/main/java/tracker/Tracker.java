package tracker;

import java.util.Arrays;
import java.util.Random;

/**
 * Система ведения заявок.
 * @author Aleksey Kochetkov
 */
public class Tracker {
    private Item[] items = new Item[100];
    private int position = 0;
//    private static long nextId = 1;
    private static final Random RANDOM = new Random();

    public Item add(Item item) {
        item.setId(this.generateId());
        items[position++] = item;
        return item;
    }

    public void replace(String id, Item item) {
        for (int i = 0; i < this.position; i++) {
            if (this.items[i].getId().equals(id)) {
                this.items[i] = item;
            }
        }
    }

    public void delete(String id) {
        for (int i = 0; i < this.position; i++) {
            if (this.items[i].getId().equals(id)) {
                System.arraycopy(this.items, i + 1, this.items, i, position - i);
                position--;
                break;
            }
        }
    }

    public Item[] getAll() {
        return Arrays.copyOf(items, position);
    }

    public Item findById(String id) {
        Item result = null;
        for (int i = 0; i < this.position; i++) {
            if (this.items[i].getId().equals(id)) {
                result = this.items[i];
                break;
            }
        }
        return result;
    }

    public Item[] findByName(String name) {
        Item[] items = new Item[this.position];
        int found = 0;
        for (int i = 0; i < this.position; i++) {
            if (this.items[i].getName().equals(name)) {
                items[found++] = this.items[i];
            }
        }
        return Arrays.copyOf(items, found);
    }

    private String generateId() {
//        return Long.toString(this.nextId++);
        return String.valueOf(System.currentTimeMillis() + RANDOM.nextInt());
    }
}
