package e;

import java.sql.Timestamp;
import java.util.Date;
import java.text.SimpleDateFormat;

public class Ad {
    public static class Builder {
        private int id;
        private User user;
        private Model model;
        private CarBody carBody;
        private Engine engine;
        private Transmission transmission;
        private boolean closed;
        private int year;
        private int km;
        private int price;
        private Image image;

        public Builder(User user, Model model, CarBody carBody,
            Engine engine, Transmission transmission, int year, int km) {
            this.user = user;
            this.model = model;
            this.carBody = carBody;
            this.engine = engine;
            this.transmission = transmission;
            this.year = year;
            this.km = km;
        }

        public int getId() {
            return id;
        }

        public Builder withId(int id) {
            this.id = id;
            return this;
        }

        public User getUser() {
            return this.user;
        }

        public Model getModel() {
            return model;
        }

        public CarBody getCarBody() {
            return carBody;
        }

        public Engine getEngine() {
            return engine;
        }

        public Transmission getTransmission() {
            return transmission;
        }

        public boolean isClosed() {
            return closed;
        }

        public Builder withClosed(boolean closed) {
            this.closed = closed;
            return this;
        }

        public int getYear() {
            return year;
        }

        public Builder withYear(int year) {
            this.year = year;
            return this;
        }

        public int getKm() {
            return km;
        }

        public Builder withKm(int km) {
            this.km = km;
            return this;
        }

        public int getPrice() {
            return price;
        }

        public Builder withPrice(int price) {
            this.price = price;
            return this;
        }

        public Image getImage() {
            return image;
        }

        public Builder withImage(Image image) {
            this.image = image;
            return this;
        }

        public Ad build() {
            return new Ad(this);
        }
    }

    private static final SimpleDateFormat F =
                                  new SimpleDateFormat("d MMM YY HH:mm");
    private int id;
    private User user;
    private Model model;
    private CarBody carBody;
    private Engine engine;
    private Transmission transmission;
    private Timestamp createDt;
    private boolean closed;
    private int year;
    private int km;
    private int price;
    private Image image;

    public Ad() {
    }

    public Ad(Builder builder) {
        this.id = builder.getId();
        this.user = builder.getUser();
        this.model = builder.getModel();
        this.carBody = builder.getCarBody();
        this.engine = builder.getEngine();
        this.transmission = builder.getTransmission();
        this.closed = builder.isClosed();
        this.year = builder.getYear();
        this.km = builder.getKm();
        this.price = builder.getPrice();
        this.image = builder.getImage();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public User getUser() {
        return this.user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Model getModel() {
        return this.model;
    }

    public void setModel(Model model) {
        this.model = model;
    }

    public CarBody getCarBody() {
        return this.carBody;
    }

    public void setCarBody(CarBody carBody) {
        this.carBody = carBody;
    }

    public Engine getEngine() {
        return this.engine;
    }

    public void setEngine(Engine engine) {
        this.engine = engine;
    }

    public Transmission getTransmission() {
        return transmission;
    }

    public void setTransmission(Transmission transmission) {
        this.transmission = transmission;
    }

    public Timestamp getCreateDt() {
        return this.createDt;
    }

    public void setCreateDt(Timestamp createDt) {
        this.createDt = createDt;
    }

    public boolean isClosed() {
        return closed;
    }

    public void setClosed(boolean closed) {
        this.closed = closed;
    }

    public int getYear() {
        return this.year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getKm() {
        return this.km;
    }

    public void setKm(int km) {
        this.km = km;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public Image getImage() {
        return this.image;
    }

    public void setImage(Image image) {
        this.image = image;
    }

    public String getFormattedCreateDt() {
        return F.format(new Date(this.createDt.getTime()));
    }

    @Override
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof Ad)) {
            return false;
        }
        Ad a = (Ad) o;
        return (a.id == this.id)
            && (a.user == null ? this.user == null
                                              : a.user.equals(this.user))
            && (a.model == null ? this.model == null
                                            : a.model.equals(this.model))
            && (a.carBody == null ? this.carBody == null
                                        : a.carBody.equals(this.carBody))
            && (a.engine == null ? this.engine == null
                                          : a.engine.equals(this.engine))
            && (a.transmission == null ? this.transmission == null
                              : a.transmission.equals(this.transmission))
            && (a.createDt == null ? this.createDt == null
                                      : a.createDt.equals(this.createDt))
            && (a.closed == this.closed)
            && (a.year == this.year)
            && (a.km == this.km)
            && (a.price == this.price)
            && (a.image == null ? this.image == null
                                           : a.image.equals(this.image));
    }

    @Override
    public int hashCode() {
        int result = this.id;
        result += 31 * result + (this.user == null ? 0 : this.user.hashCode());
        result += 31 * result + (this.model == null ? 0 : this.model.hashCode());
        result += 31 * result + (this.carBody == null ? 0 : this.carBody.hashCode());
        result += 31 * result + (this.engine == null ? 0 : this.engine.hashCode());
        result += 31 * result + (this.transmission == null ? 0 : this.transmission.hashCode());
        result += 31 * result + (this.createDt == null ? 0 : this.createDt.hashCode());
        result += 31 * result + (this.closed ? 1 : 0);
        result += 31 * result + this.year;
        result += 31 * result + this.km;
        result += 31 * result + this.price;
        result += 31 * result + (this.image == null ? 0 : this.image.hashCode());
        return result;
    }
}
