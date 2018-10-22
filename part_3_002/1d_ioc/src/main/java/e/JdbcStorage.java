package e;

import org.hibernate.cfg.Configuration;
import org.hibernate.SessionFactory;
import org.hibernate.Session;

public class JdbcStorage implements Storage {
    private final SessionFactory factory;

    public JdbcStorage(String resource) {
        this.factory = new Configuration().configure(resource)
                                                  .buildSessionFactory();
    }

    @Override
    public void createUser(User user) {
        Session session = this.factory.openSession();
        try {
            session.beginTransaction();
            session.save(user);
            session.getTransaction().commit();
        } catch (Exception exception) {
            session.getTransaction().rollback();
            throw exception;
        } finally {
            session.close();
        }
    }

    @Override
    public int countUsers() {
        Session session = this.factory.openSession();
        try {
            session.beginTransaction();
            int result = (Integer) session.createQuery(
                "SELECT new Integer(count(*)) FROM User").uniqueResult();
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
    public void close() {
        this.factory.close();
    }
}
