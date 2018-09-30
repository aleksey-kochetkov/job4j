package e;

public class Transmission {
    private int id;
    private String descript;

    public Transmission() {
    }

    public Transmission(String descript) {
        this.descript = descript;
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
}
