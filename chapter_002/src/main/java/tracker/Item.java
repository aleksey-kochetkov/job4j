package tracker;

/**
 * Заявка.
 * @author Aleksey Kochetkov
 */
public class Item {
    private String name;
    private String description;
    private String id;

    public Item(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public String getName() {
        return this.name;
    }

    public String getDescription() {
        return this.description;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getId() {
        return this.id;
    }
}
