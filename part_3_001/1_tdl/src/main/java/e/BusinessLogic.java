package e;

import org.hibernate.cfg.Configuration;
import org.hibernate.SessionFactory;
import org.hibernate.Session;
import org.hibernate.query.Query;
import java.util.List;
import java.util.function.Function;

public class BusinessLogic {
    private SessionFactory factory = new Configuration()
                                      .configure().buildSessionFactory();

    void close() {
        this.factory.close();
    }

    Item createItem(String descript) {
        Item item = new Item(descript);
        wrapper(session -> session.save(item));
        return item;
    }

    List<Item> findAllItems(String doneStr) {
        return this.wrapper(session -> {
            String sql = Boolean.parseBoolean(doneStr) ? "from Item"
                                        : "from Item where done = false";
            Query<Item> query = session.createQuery(sql);
            return query.list();
        });
    }

    Item setItemDone(String idStr, String doneStr) {
        Item item = new Item();
        return this.wrapper(session -> {
            session.load(item, Integer.parseInt(idStr));
            item.setDone(Boolean.parseBoolean(doneStr));
            return item;
        });
    }

    private <T> T wrapper(Function<Session, T> function) {
        Session session = this.factory.openSession();
        session.getTransaction().begin();
        try {
            T result = function.apply(session);
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
