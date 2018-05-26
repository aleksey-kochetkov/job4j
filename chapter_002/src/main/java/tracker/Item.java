package tracker;

/**
 * Заявка.
 * @author Aleksey Kochetkov
 */
public class Item {
    private String name;
    private String description;
    private String id;

    public Item(String name, String description, String id) {
        this.name = name;
        this.description = description;
        this.id = id;
    }

    public String getName() {
        return this.name;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getId() {
        return this.id;
    }
}
