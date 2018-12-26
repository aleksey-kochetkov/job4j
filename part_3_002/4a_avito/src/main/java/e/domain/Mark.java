package e.domain;

public class Mark {
    private int id;
    private String name;

    public Mark() {
    }

    public Mark(int id) {
        this.id = id;
    }

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof Mark)) {
            return false;
        }
        Mark m = (Mark) o;
        return (m.id == this.id) && (m.name == null ? this.name == null
                                             : m.name.equals(this.name));
    }

    @Override
    public int hashCode() {
        int result = this.id;
        result = 31 * result + (this.name == null ? 0
                                                 : this.name.hashCode());
        return result;
    }
}
