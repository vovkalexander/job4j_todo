package ru.job4j.todolist.simple1;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import ru.job4j.todolist.simple.CarModel;

public class HbmDriver {
    public static void main(String[] args) {
        final StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
                .configure().build();
        try {
            SessionFactory sf = new MetadataSources(registry).buildMetadata().buildSessionFactory();
            Session session = sf.openSession();
            session.beginTransaction();
            Engine engine =  Engine.of("V8");
            session.save(engine);
            Car car = Car.of("Pagani_Huayra");
            Driver driver = Driver.of("boozer");
            session.save(driver);
            Driver driver1 = Driver.of("Toper");
            session.save(driver1);
            car.setEngine(engine);
            car.addDriver(session.load(Driver.class, 1));
            car.addDriver(session.load(Driver.class, 2));
            session.save(car);
            session.getTransaction().commit();
            session.close();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            StandardServiceRegistryBuilder.destroy(registry);
        }
    }
}
