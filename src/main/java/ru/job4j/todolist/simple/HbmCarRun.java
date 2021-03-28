package ru.job4j.todolist.simple;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

public class HbmCarRun {
    public static void main(String[] args) {
        final StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
                .configure().build();
        try {
            SessionFactory sf = new MetadataSources(registry).buildMetadata().buildSessionFactory();
            Session session = sf.openSession();
            session.beginTransaction();
            CarModel one = CarModel.of("polo");
            session.save(one);
            CarModel two  = CarModel.of("passat");
            session.save(two);
            CarModel three  = CarModel.of("tiguan");
            session.save(three);
            CarModel four  = CarModel.of("sirocco");
            session.save(four);
            CarModel five  = CarModel.of("caddy");
            session.save(five);
            Brand brand = Brand.of("VOLKSWAGEN");
            brand.addUser(session.load(CarModel.class, 1));
            brand.addUser(session.load(CarModel.class, 2));
            brand.addUser(session.load(CarModel.class, 3));
            brand.addUser(session.load(CarModel.class, 4));
            brand.addUser(session.load(CarModel.class, 5));
            session.save(brand);
            session.getTransaction().commit();
            session.close();
        }  catch (Exception e) {
            e.printStackTrace();
        } finally {
            StandardServiceRegistryBuilder.destroy(registry);
        }
    }
}
