package e;

import org.hibernate.cfg.Configuration;
import org.hibernate.SessionFactory;
import org.hibernate.Session;
import org.hibernate.query.Query;
import java.util.List;

public class BusinessLogic {

    Item createItem(String descript) {
        Item result;
        try (SessionFactory factory =
                   new Configuration().configure().buildSessionFactory();
             Session session = factory.openSession()) {
            result = new Item(descript);
            session.save(result);
        }
        return result;
    }

    List<Item> findAllItems(String doneStr) {
        try (SessionFactory factory =
                   new Configuration().configure().buildSessionFactory();
             Session session = factory.openSession()) {
            String sql = Boolean.parseBoolean(doneStr) ? "from Item"
                                        : "from Item where done = false";
            Query<Item> query = session.createQuery(sql);
            return query.list();
        }
    }

    Item setItemDone(String idStr, String doneStr) {
        try (SessionFactory factory =
                   new Configuration().configure().buildSessionFactory();
             Session session = factory.openSession()) {
            session.getTransaction().begin();
            Item result = new Item();
            session.load(result, Integer.parseInt(idStr));
            result.setDone(Boolean.parseBoolean(doneStr));
            session.getTransaction().commit();
            return result;
        }
    }
}
