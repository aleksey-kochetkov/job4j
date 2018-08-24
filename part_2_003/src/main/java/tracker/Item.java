package tracker;

/**
 * Заявка.
 * @author Aleksey Kochetkov
 */
public class Item {
    private String name;
    private String description;
    private int id;

    public Item(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public Item(int id, String name, String description) {
        this.id = id;
        this.name = name;
        this.description = description;
    }

    public String getName() {
        return this.name;
    }

    public String getDescription() {
        return this.description;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return this.id;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof Item)) {
            return false;
        }
        Item other = (Item) o;
        if (this.id != other.id) {
            return false;
        }
        if (!(this.name == null ? other.name == null : this.name.equals(other.name))) {
            return false;
        }
        if (!(this.description == null ? other.description == null
                         : this.description.equals(other.description))) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        int result = Integer.hashCode(this.id);
        result = 31 * result + this.name.hashCode();
        return 31 * result + this.description.hashCode();
    }
}
