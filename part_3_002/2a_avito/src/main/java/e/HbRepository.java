package e;

import org.hibernate.cfg.Configuration;
import org.hibernate.SessionFactory;
import org.hibernate.Session;
import org.hibernate.Criteria;
import org.hibernate.query.Query;
import org.hibernate.criterion.Restrictions;

import java.util.Date;
import java.util.Optional;
import java.util.List;
import org.springframework.stereotype.Component;

@Component
public class HbRepository implements Repository {
    private static final SessionFactory FACTORY = new Configuration()
                                      .configure().buildSessionFactory();

    public HbRepository() {
    }

    @Override
    public void close() {
        FACTORY.close();
    }

    @Override
    public User createUser(String login, String name, String password) {
        Session session = FACTORY.openSession();
        try {
            session.beginTransaction();
            User user = new User(login, name, password);
            session.save(user);
            session.getTransaction().commit();
            return user;
        } catch (Exception exception) {
            session.getTransaction().rollback();
            throw exception;
        } finally {
            session.close();
        }
    }

    @Override
    public User findUserByLogin(String login) {
        Session session = FACTORY.openSession();
        try {
            session.beginTransaction();
            Query<User> query = session.<User>createQuery(
                                       "FROM User WHERE login = :login");
            query.setParameter("login", login);
            List<User> list = query.list();
            session.getTransaction().commit();
            return list.size() == 0 ? null : list.get(0);
        } catch (Exception exception) {
            session.getTransaction().rollback();
            throw exception;
        } finally {
            session.close();
        }
    }

    @Override
    public Mark createMark(Mark mark) {
        Session session = FACTORY.openSession();
        try {
            session.beginTransaction();
            session.save(mark);
            session.getTransaction().commit();
            return mark;
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
    public Model createModel(Model model) {
        Session session = FACTORY.openSession();
        try {
            session.beginTransaction();
            session.save(model);
            session.getTransaction().commit();
            return model;
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
    public CarBody createCarBody() {
        Session session = FACTORY.openSession();
        try {
            session.beginTransaction();
            CarBody carBody = new CarBody();
            session.save(carBody);
            session.getTransaction().commit();
            return carBody;
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
    public Engine createEngine() {
        Session session = FACTORY.openSession();
        try {
            session.beginTransaction();
            Engine engine = new Engine();
            session.save(engine);
            session.getTransaction().commit();
            return engine;
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
    public Transmission createTransmission() {
        Session session = FACTORY.openSession();
        try {
            session.beginTransaction();
            Transmission transmission = new Transmission();
            session.save(transmission);
            session.getTransaction().commit();
            return transmission;
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
            List<Ad> result = session.<Ad>createQuery("FROM Ad").list();
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
    public List<Ad> findAds(Optional<Integer> markId, Date begin,
                                              Date end, Boolean closed) {
        Session session = FACTORY.openSession();
        try {
            session.beginTransaction();
            Criteria criteria = session.<Ad>createCriteria(Ad.class);
            if (markId.isPresent()) {
                Mark mark = new Mark(markId.get());
                criteria = criteria.createAlias("model", "model")
                               .add(Restrictions.eq("model.mark", mark));
            }
            if (begin != null && end != null) {
                criteria.add(Restrictions
                                       .between("createDt", begin, end));
            }
            if (closed != null) {
                criteria.add(Restrictions.eq("closed", closed));
            }
            List<Ad> result = criteria.list();
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
