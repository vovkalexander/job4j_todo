package ru.job4j.todolist.model;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import java.util.Collection;
import java.util.Date;
import java.util.function.Function;

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
        return this.tx(session ->
                session.createQuery("from ru.job4j.todolist.model.Item order by id").list());
    }

    @Override
    public Item save(String text) {
        return this.tx(session -> {
            Item item = new Item();
            item.setCreated(new Date());
            item.setDescription(text);
            session.save(item);
            return item;
        });
    }

    @Override
    public Item findItemById(int id) {
        return this.tx(session -> session.byId(Item.class).load(id));
    }

    @Override
    public void replace(int id) {
        Item item = this.findItemById(id);
        item.setDone(true);
        this.tx(session -> {
            session.update(item);
            return null;
        });
    }

    @Override
    public void close() throws Exception {
        StandardServiceRegistryBuilder.destroy(registry);
    }

    private <T> T tx(final Function<Session, T> command) {
        final Session session = sf.openSession();
        final Transaction tx = session.beginTransaction();
        try {
            T rsl = command.apply(session);
            tx.commit();
            return rsl;
        } catch (final Exception e) {
            session.getTransaction().rollback();
            throw e;
        } finally {
            session.close();
        }
    }
}
