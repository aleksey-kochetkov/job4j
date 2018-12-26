package e;

public class Entry {
    int id;
    String name;

    public Entry(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof Entry)) {
            return false;
        }
        Entry e = (Entry) o;
        return e.id == this.id
           && (e.name == null ? this.name == null
                                             : e.name.equals(this.name));
    }

    @Override
    public int hashCode() {
        int result = this.id;
        return 31 * result + (this.name == null ? 0
                                                 : this.name.hashCode());
    }
}
