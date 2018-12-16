package e.domain;

public class Transmission {
    private int id;
    private String descript;

    public Transmission() {
    }

    public Transmission(int id) {
        this.id = id;
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

    @Override
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof Transmission)) {
            return false;
        }
        Transmission t = (Transmission) o;
        return (t.id == this.id)
            && (t.descript == null ? this.descript == null
                                     : t.descript.equals(this.descript));
    }

    @Override
    public int hashCode() {
        int result = this.id;
        result += 31 * result + (this.descript == null ? 0
                                             : this.descript.hashCode());
        return result;
    }
}
