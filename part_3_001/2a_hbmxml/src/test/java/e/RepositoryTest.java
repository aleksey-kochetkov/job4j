package e;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;
import static org.hamcrest.core.Is.is;

public class RepositoryTest {
    private Repository repository;

    @Before
    public void before() {
        this.repository = new Repository();
        this.repository.clear();
    }

    @Test
    public void whenCreateCarBody() {
        CarBody cb = this.repository.createCarBody("Кузов 01");
        assertThat(this.repository.findCarBodyById(
                              cb.getId()).getDescript(), is("Кузов 01"));
    }

    @Test
    public void whenUpdateCarBody() {
        CarBody cb = this.repository.createCarBody("Кузов 02");
        cb.setDescript("Кузов 03");
        this.repository.updateCarBody(cb);
        assertThat(this.repository.findCarBodyById(
                              cb.getId()).getDescript(), is("Кузов 03"));
    }

    @Test
    public void whenDeleteCarBody() {
        CarBody cb = this.repository.createCarBody("Кузов 04");
        this.repository.deleteCarBody(cb);
        assertNull(this.repository.findCarBodyById(cb.getId()));
    }

    @Test
    public void whenCreateEnging() {
        Engine e = this.repository.createEngine("Двигатель 01");
        assertThat(this.repository.findEngineById(
                           e.getId()).getDescript(), is("Двигатель 01"));
    }

    @Test
    public void whenUpdateEngine() {
        Engine e = this.repository.createEngine("Двигатель 02");
        e.setDescript("Двигатель 03");
        this.repository.updateEngine(e);
        assertThat(this.repository.findEngineById(
                           e.getId()).getDescript(), is("Двигатель 03"));
    }

    @Test
    public void whenDeleteEngine() {
        Engine e = this.repository.createEngine("Двигатель 04");
        this.repository.deleteEngine(e);
        assertNull(this.repository.findEngineById(e.getId()));
    }

    @Test
    public void whenCreateTransmission() {
        Transmission t = this.repository
                               .createTransmission("Коробка передач 01");
        assertThat(this.repository.findTransmissionById(
                     t.getId()).getDescript(), is("Коробка передач 01"));
    }

    @Test
    public void whenUpdateTransmission() {
        Transmission t = this.repository
                               .createTransmission("Коробка передач 02");
        t.setDescript("Коробка передач 03");
        this.repository.updateTransmission(t);
        assertThat(this.repository.findTransmissionById(
                     t.getId()).getDescript(), is("Коробка передач 03"));
    }

    @Test
    public void whenDeleteTransmission() {
        Transmission t = this.repository
                               .createTransmission("Коробка передач 04");
        this.repository.deleteTransmission(t);
        assertNull(this.repository.findEngineById(t.getId()));
    }

    @Test
    public void whenCreateCar() {
        Car car = this.repository.createCar("Машина 05 05 05",
                       "Кузов 05", "Двигатель 05", "Коробка передач 05");
        car = this.repository.findCarById(car.getId());
        assertThat(car.getCarBody().getDescript(), is("Кузов 05"));
        assertThat(car.getEngine().getDescript(), is("Двигатель 05"));
        assertThat(car.getTransmission().getDescript(),
                                               is("Коробка передач 05"));
    }

    @Test
    public void whenUpdateCar() {
        Car car = this.repository.createCar("Машина 06 06 06",
                       "Кузов 06", "Двигатель 06", "Коробка передач 06");
        car.setCarBody(this.repository.createCarBody("Кузов 07"));
        car.setEngine(this.repository.createEngine("Двигатель 07"));
        car.setTransmission(
               this.repository.createTransmission("Коробка передач 07"));
        this.repository.updateCar(car);
        car = this.repository.findCarById(car.getId());
        assertThat(car.getCarBody().getDescript(), is("Кузов 07"));
        assertThat(car.getEngine().getDescript(), is("Двигатель 07"));
        assertThat(car.getTransmission().getDescript(),
                                               is("Коробка передач 07"));
    }

    @Test
    public void whenDeleteCar() {
        Car car = this.repository.createCar("Машина 08 08 08",
                       "Кузов 08", "Двигатель 08", "Коробка передач 08");
        this.repository.deleteCar(car);
        assertNull(this.repository.findCarById(car.getId()));
    }

    @Test
    public void whenDeleteCarNoCascade() {
        Car car = this.repository.createCar("Машина 09 09 09",
                       "Кузов 09", "Двигатель 09", "Коробка передач 09");
        CarBody cb = car.getCarBody();
        Engine e = car.getEngine();
        Transmission t = car.getTransmission();
        this.repository.deleteCar(car);
        assertNotNull(this.repository.findCarBodyById(cb.getId()));
        assertNotNull(this.repository.findEngineById(e.getId()));
        assertNotNull(this.repository.findTransmissionById(t.getId()));
    }
}
