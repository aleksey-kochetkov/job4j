package e.domain;

public class Model {
    private int id;
    private Mark mark;
    private String name;

    public Model() {
    }

    public Model(Mark mark) {
        this.mark = mark;
    }

    public Model(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Mark getMark() {
        return this.mark;
    }

    public void setMark(Mark mark) {
        this.mark = mark;
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
        if (!(o instanceof Model)) {
            return false;
        }
        Model m = (Model) o;
        return m.id == this.id
           && (m.mark == null ? this.mark == null
                                              : m.mark.equals(this.mark))
           && (m.name == null ? this.name == null
                                             : m.name.equals(this.name));
    }

    @Override
    public int hashCode() {
        int result = this.id;
        result += 31 * result + (this.mark == null ? 0
                                                 : this.mark.hashCode());
        result += 31 * result + (this.name == null ? 0
                                                 : this.name.hashCode());
        return result;
    }
}
