package e;

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
}
