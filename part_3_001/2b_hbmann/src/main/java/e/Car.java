package e;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Id;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.ManyToOne;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;
import javax.persistence.JoinColumn;

@Entity
@Table(name = "car")
public class Car {
    private int id;
    private String name;
    private CarBody carBody;
    private Engine engine;
    private Transmission transmission;

    public Car() {
    }

    public Car(String name, CarBody carBody, Engine engine,
               Transmission transmission) {
        this.name = name;
        this.carBody = carBody;
        this.engine = engine;
        this.transmission = transmission;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @ManyToOne
    @JoinColumn(name = "car_body_id")
    @Cascade(value = CascadeType.SAVE_UPDATE)
    public CarBody getCarBody() {
        return carBody;
    }

    public void setCarBody(CarBody carBody) {
        this.carBody = carBody;
    }

    @ManyToOne
    @Cascade(value = CascadeType.SAVE_UPDATE)
    public Engine getEngine() {
        return engine;
    }

    public void setEngine(Engine engine) {
        this.engine = engine;
    }

    @ManyToOne
    @Cascade(value = CascadeType.SAVE_UPDATE)
    public Transmission getTransmission() {
        return transmission;
    }

    public void setTransmission(Transmission transmission) {
        this.transmission = transmission;
    }
}
