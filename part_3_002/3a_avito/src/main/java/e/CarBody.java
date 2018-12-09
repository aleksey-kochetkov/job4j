package e;

public class CarBody {
    private int id;
    private String descript;

    public CarBody() {
    }

    public CarBody(int id) {
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
        if (!(o instanceof CarBody)) {
            return false;
        }
        CarBody cb = (CarBody) o;
        return (cb.id == this.id)
                && (cb.descript == null ? this.descript == null
                                    : cb.descript.equals(this.descript));
    }

    @Override
    public int hashCode() {
        int result = this.id;
        result += 31 * result + (this.descript == null ? 0
                                             : this.descript.hashCode());
        return result;
    }
}
