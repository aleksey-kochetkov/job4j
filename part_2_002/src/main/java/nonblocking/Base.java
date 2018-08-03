package nonblocking;

public class Base {
    private int id;
    private int version;

    public Base(int id) {
        this.id = id;
    }

    public int getId() {
        return this.id;
    }

    public int getVersion() {
        return this.version;
    }

    public void resetVersion() {
        this.version = 0;
    }

    public void incVersion() {
        this.version++;
    }

    void setDeleted() {
        this.version = -1;
    }
}
