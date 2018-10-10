package e;

import org.hibernate.cfg.Configuration;
import org.hibernate.SessionFactory;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.util.List;

public class HbRepository implements Repository {
    private static final SessionFactory FACTORY = new Configuration()
                                      .configure().buildSessionFactory();

    @Override
    public void close() {
        FACTORY.close();
    }

    @Override
    public User findUserByLogin(String login) {
        Session session = FACTORY.openSession();
        try {
            session.beginTransaction();
            Query<User> query = session.<User>createQuery(
                                       "FROM User WHERE login = :login");
            query.setParameter("login", login);
            User result = query.list().get(0);
            session.getTransaction().commit();
            return result;
        } catch (Exception exception) {
            session.getTransaction().rollback();
            throw exception;
        } finally {
            session.close();
        }
    }

    @Override
    public List<Mark> findAllMarks() {
        Session session = FACTORY.openSession();
        try {
            session.beginTransaction();
            List<Mark> result = session.<Mark>createQuery(
                                       "FROM Mark ORDER BY name").list();
            session.getTransaction().commit();
            return result;
        } catch (Exception exception) {
            session.getTransaction().rollback();
            throw exception;
        } finally {
            session.close();
        }
    }

    @Override
    public List<Model> findModelsByMarkId(int markId) {
        Session session = FACTORY.openSession();
        try {
            session.beginTransaction();
            Query<Model> query = session.<Model>createQuery(
                     "FROM Model WHERE mark_id = :markId ORDER BY name");
            query.setParameter("markId", markId);
            session.getTransaction().commit();
            return query.list();
        } catch (Exception exception) {
            session.getTransaction().rollback();
            throw exception;
        } finally {
            session.close();
        }
    }

    @Override
    public List<CarBody> findAllCarBodies() {
        Session session = FACTORY.openSession();
        try {
            session.beginTransaction();
            List<CarBody> result = session.<CarBody>createQuery(
                                                  "FROM CarBody").list();
            session.getTransaction().commit();
            return result;
        } catch (Exception exception) {
            session.getTransaction().rollback();
            throw exception;
        } finally {
            session.close();
        }
    }

    @Override
    public List<Engine> findAllEngines() {
        Session session = FACTORY.openSession();
        try {
            session.beginTransaction();
            List<Engine> result = session.<Engine>createQuery(
                                                   "FROM Engine").list();
            session.getTransaction().commit();
            return result;
        } catch (Exception exception) {
            session.getTransaction().rollback();
            throw exception;
        } finally {
            session.close();
        }
    }

    @Override
    public List<Transmission> findAllTransmissions() {
        Session session = FACTORY.openSession();
        try {
            session.beginTransaction();
            List<Transmission> result = session
                  .<Transmission>createQuery("FROM Transmission").list();
            session.getTransaction().commit();
            return result;
        } catch (Exception exception) {
            session.getTransaction().rollback();
            throw exception;
        } finally {
            session.close();
        }
    }

    @Override
    public void createAd(Ad ad) {
        Session session = FACTORY.openSession();
        try {
            session.beginTransaction();
            session.save(ad);
            session.refresh(ad);
            Image image = ad.getImage();
            image.setId(ad.getId());
            session.saveOrUpdate(image);
            session.getTransaction().commit();
        } catch (Exception exception) {
            session.getTransaction().rollback();
            throw exception;
        } finally {
            session.close();
        }
    }

    @Override
    public Ad findAdById(int id) {
        Session session = FACTORY.openSession();
        try {
            session.beginTransaction();
            Query<Ad> query = session.<Ad>createQuery(
                                               "FROM Ad WHERE id = :id");
            query.setParameter("id", id);
            Ad result = query.list().get(0);
            session.getTransaction().commit();
            return result;
        } catch (Exception exception) {
            session.getTransaction().rollback();
            throw exception;
        } finally {
            session.close();
        }
    }

    @Override
    public List<Ad> findAllAds() {
        Session session = FACTORY.openSession();
        try {
            session.beginTransaction();
            List<Ad> result = session
                  .<Ad>createQuery("FROM Ad").list();
            session.getTransaction().commit();
            return result;
        } catch (Exception exception) {
            session.getTransaction().rollback();
            throw exception;
        } finally {
            session.close();
        }
    }

    @Override
    public void updateAd(Ad ad) {
        Session session = FACTORY.openSession();
        try {
            session.beginTransaction();
            session.saveOrUpdate(ad);
            session.flush();
            session.refresh(ad);
            Image image = ad.getImage();
            if (image.getBytes() != null) {
                image.setId(ad.getId());
                session.saveOrUpdate(image);
            }
            session.getTransaction().commit();
        } catch (Exception exception) {
            session.getTransaction().rollback();
            throw exception;
        } finally {
            session.close();
        }
    }

    @Override
    public Image findImageById(int id) {
        Session session = FACTORY.openSession();
        try {
            session.beginTransaction();
            Query<Image> query = session.<Image>createQuery(
                                            "FROM Image WHERE id = :id");
            query.setParameter("id", id);
            Image result = query.list().get(0);
            session.getTransaction().commit();
            return result;
        } catch (Exception exception) {
            session.getTransaction().rollback();
            throw exception;
        } finally {
            session.close();
        }
    }
}
