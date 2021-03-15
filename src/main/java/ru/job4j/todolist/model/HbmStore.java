package ru.job4j.todolist.model;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

public class HbmStore implements Store, AutoCloseable {
    private final StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
            .configure().build();
    private final SessionFactory sf = new MetadataSources(registry)
            .buildMetadata().buildSessionFactory();

    private static final class Lazy {
        private static final Store INST = new HbmStore();
    }

    public static Store instOf() {
        return Lazy.INST;
    }

    @Override
    public Collection<Item> findAllItems() {
        Collection<Item> result = new ArrayList();
        try (Session session = sf.openSession()) {
            session.beginTransaction();
            result = session.createQuery("from ru.job4j.todolist.model.Item").list();
            session.getTransaction().commit();
            session.close();
        } catch (Exception e) {
            sf.getCurrentSession().getTransaction().rollback();
        }
        return result;
    }

    @Override
    public Item save(String text) {
        Session session = sf.openSession();
        session.beginTransaction();
        Item item = new Item();
        item.setCreated(new Date());
        item.setDescription(text);
        session.save(item);
        session.getTransaction().commit();
        session.close();
        return item;
    }

    @Override
    public Item findItemById(int id) {
        Item item = null;
        try (Session session = sf.openSession()) {
            session.beginTransaction();
            item = (Item) session.byId(Item.class).load(id);
            session.getTransaction().commit();
        } catch (Exception e) {
            sf.getCurrentSession().getTransaction().rollback();
        }
        return item;
    }

    @Override
    public void replace(int id) {
        Item item = this.findItemById(id);
        System.out.println(item);
        item.setDone(true);
        try (Session session = sf.openSession()) {
            session.beginTransaction();
            session.update(item);
            session.getTransaction().commit();
        } catch (Exception e) {
            sf.getCurrentSession().getTransaction().rollback();
        }
    }

    @Override
    public void close() throws Exception {
        StandardServiceRegistryBuilder.destroy(registry);
    }
}
