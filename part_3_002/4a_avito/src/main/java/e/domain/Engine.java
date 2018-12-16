package e.domain;

public class Engine {
    private int id;
    private String descript;

    public Engine() {
    }

    public Engine(int id) {
        this.id = id;
    }

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescript() {
        return this.descript;
    }

    public void setDescript(String descript) {
        this.descript = descript;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof Engine)) {
            return false;
        }
        Engine e = (Engine) o;
        return e.id == this.id
           && (e.descript == null ? this.descript == null
                                     : e.descript.equals(this.descript));
    }

    @Override
    public int hashCode() {
        int result = this.id;
        result += 31 * result + (this.descript == null ? 0
                                             : this.descript.hashCode());
        return result;
    }
}
