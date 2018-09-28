package e;

import java.sql.Timestamp;

public class Item {
    private int id;
    private String descript;
    private Timestamp created;
    private boolean done;

    public Item() {
    }

    public Item(int id, boolean done) {
        this.id = id;
        this.done = done;
    }

    public Item(String descript) {
        this.descript = descript;
        this.created = new Timestamp(System.currentTimeMillis());
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescript() {
        return descript;
    }

    public void setDescript(String descript) {
        this.descript = descript;
    }

    public Timestamp getCreated() {
        return created;
    }

    public void setCreated(Timestamp created) {
        this.created = created;
    }

    public boolean isDone() {
        return done;
    }

    public void setDone(boolean done) {
        this.done = done;
    }
}
