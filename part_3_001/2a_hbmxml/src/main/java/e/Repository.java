package e;

import org.hibernate.cfg.Configuration;
import org.hibernate.SessionFactory;
import org.hibernate.Session;
import org.hibernate.query.Query;
import java.util.List;

public class Repository {
    private static final SessionFactory FACTORY = new Configuration()
                                      .configure().buildSessionFactory();

    public CarBody createCarBody(String descript) {
        Session session = FACTORY.openSession();
        try {
            CarBody result = new CarBody(descript);
            session.beginTransaction();
            session.save(result);
            session.getTransaction().commit();
            return result;
        } catch (Exception exception) {
            session.getTransaction().rollback();
            throw exception;
        } finally {
            session.close();
        }
    }

    public CarBody findCarBodyById(int id) {
        Session session = FACTORY.openSession();
        try {
            session.beginTransaction();
            Query<CarBody> query = session.createQuery("FROM CarBody WHERE id = :id");
            query.setParameter("id", id);
            CarBody result = null;
            for (CarBody cb : query.list()) {
                result = cb;
            }
            session.getTransaction().commit();
            return result;
        } catch (Exception exception) {
            session.getTransaction().rollback();
            throw exception;
        } finally {
            session.close();
        }
    }

    public void updateCarBody(CarBody carBody) {
        Session session = FACTORY.openSession();
        try {
            session.beginTransaction();
            session.update(carBody);
            session.getTransaction().commit();
        } catch (Exception exception) {
            session.getTransaction().rollback();
            throw exception;
        } finally {
            session.close();
        }
    }

    public void deleteCarBody(CarBody carBody) {
        Session session = FACTORY.openSession();
        try {
            session.beginTransaction();
            session.delete(carBody);
            session.getTransaction().commit();
        } catch (Exception exception) {
            session.getTransaction().rollback();
            throw exception;
        } finally {
            session.close();
        }
    }

    public Engine createEngine(String descript) {
        Session session = FACTORY.openSession();
        try {
            Engine result = new Engine(descript);
            session.beginTransaction();
            session.save(result);
            session.getTransaction().commit();
            return result;
        } catch (Exception exception) {
            session.getTransaction().rollback();
            throw exception;
        } finally {
            session.close();
        }
    }

    public Engine findEngineById(int id) {
        Session session = FACTORY.openSession();
        try {
            Engine result = null;
            session.beginTransaction();
            Query<Engine> query = session.createQuery(
                                           "FROM Engine WHERE id = :id");
            query.setParameter("id", id);
            for (Engine e : query.list()) {
                result = e;
            }
            session.getTransaction().commit();
            return result;
        } catch (Exception exception) {
            session.getTransaction().rollback();
            throw exception;
        } finally {
            session.close();
        }
    }

    public void updateEngine(Engine engine) {
        Session session = FACTORY.openSession();
        try {
            session.beginTransaction();
            session.update(engine);
            session.getTransaction().commit();
        } catch (Exception exception) {
            session.getTransaction().rollback();
            throw exception;
        } finally {
            session.close();
        }
    }

    public void deleteEngine(Engine engine) {
        Session session = FACTORY.openSession();
        try {
            session.beginTransaction();
            session.delete(engine);
            session.getTransaction().commit();
        } catch (Exception exception) {
            session.getTransaction().rollback();
            throw exception;
        } finally {
            session.close();
        }
    }

    public Transmission createTransmission(String descript) {
        Session session = FACTORY.openSession();
        try {
            Transmission result = new Transmission(descript);
            session.beginTransaction();
            session.save(result);
            session.getTransaction().commit();
            return result;
        } catch (Exception exception) {
            session.getTransaction().rollback();
            throw exception;
        } finally {
            session.close();
        }
    }

    public Transmission findTransmissionById(int id) {
        Session session = FACTORY.openSession();
        try {
            Transmission result = null;
            session.beginTransaction();
            Query<Transmission> query = session.createQuery(
                                     "FROM Transmission WHERE id = :id");
            query.setParameter("id", id);
            for (Transmission t : query.list()) {
                result = t;
            }
            session.getTransaction().commit();
            return result;
        } catch (Exception exception) {
            session.getTransaction().rollback();
            throw exception;
        } finally {
            session.close();
        }
    }

    public void updateTransmission(Transmission transmission) {
        Session session = FACTORY.openSession();
        try {
            session.beginTransaction();
            session.update(transmission);
            session.getTransaction().commit();
        } catch (Exception exception) {
            session.getTransaction().rollback();
            throw exception;
        } finally {
            session.close();
        }
    }

    public void deleteTransmission(Transmission transmission) {
        Session session = FACTORY.openSession();
        try {
            session.beginTransaction();
            session.delete(transmission);
            session.getTransaction().commit();
        } catch (Exception exception) {
            session.getTransaction().rollback();
            throw exception;
        } finally {
            session.close();
        }
    }

    public Car createCar(String name, String carBodyDescript,
                    String engineDescript, String transmissionDescript) {
        Session session = FACTORY.openSession();
        try {
            Car result = new Car(name, new CarBody(carBodyDescript),
                                              new Engine(engineDescript),
                                 new Transmission(transmissionDescript));
            session.beginTransaction();
            session.save(result);
            return result;
        } catch (Exception exception) {
            session.getTransaction().rollback();
            throw exception;
        } finally {
            session.close();
        }
    }

    public Car findCarById(int id) {
        Session session = FACTORY.openSession();
        try {
            Car result = null;
            session.beginTransaction();
            Query<Car> query = session.createQuery("FROM Car c "
              + "JOIN FETCH c.carBody JOIN FETCH c.engine JOIN FETCH c.transmission "
              + "WHERE c.id = :id");
            query.setParameter("id", id);
            for (Car c : query.list()) {
                result = c;
            }
            session.getTransaction().commit();
            return result;
        } catch (Exception exception) {
            session.getTransaction().rollback();
            throw exception;
        } finally {
            session.close();
        }
    }

    public void updateCar(Car car) {
        Session session = FACTORY.openSession();
        try {
            session.beginTransaction();
            session.update(car);
            session.getTransaction().commit();
        } catch (Exception exception) {
            session.getTransaction().rollback();
            throw exception;
        } finally {
            session.close();
        }
    }

    public void deleteCar(Car car) {
        Session session = FACTORY.openSession();
        try {
            session.beginTransaction();
            session.delete(car);
            session.getTransaction().commit();
        } catch (Exception exception) {
            session.getTransaction().rollback();
            throw exception;
        } finally {
            session.close();
        }
    }

    public void clear() {
        Session session = FACTORY.openSession();
        try {
            session.beginTransaction();
            session.createQuery("DELETE Car").executeUpdate();
            session.createQuery("DELETE CarBody").executeUpdate();
            session.createQuery("DELETE Engine").executeUpdate();
            session.createQuery("DELETE Transmission").executeUpdate();
            session.getTransaction().commit();
        } catch (Exception exception) {
            session.getTransaction().rollback();
            throw exception;
        } finally {
            session.close();
        }
    }
}
