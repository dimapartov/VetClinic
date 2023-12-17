package VetClinic.Util;

import VetClinic.Entities.*;
import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;
import org.hibernate.service.ServiceRegistry;

import java.util.Properties;

public class HibernateUtil {
    private static SessionFactory sessionFactory;
    public static SessionFactory getSessionFactory() {
        if (sessionFactory == null) {
            try {
                Configuration configuration = new Configuration();

                Properties settings = new Properties();
                settings.put(Environment.DRIVER, "org.postgresql.Driver");
                settings.put(Environment.URL, "jdbc:postgresql://localhost/VetClinic");
                settings.put(Environment.USER, "postgres");
                settings.put(Environment.PASS, "elan0209");
                settings.put(Environment.DIALECT, "org.hibernate.dialect.PostgreSQLDialect");
//                settings.put(Environment.SHOW_SQL, "true");

                configuration.setProperties(settings);
                configuration.addAnnotatedClass(CardEntity.class);
                configuration.addAnnotatedClass(ClientEntity.class);
                configuration.addAnnotatedClass(DoctorEntity.class);
                configuration.addAnnotatedClass(OfficeEntity.class);
                configuration.addAnnotatedClass(PetEntity.class);
                configuration.addAnnotatedClass(VisitEntity.class);

                ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
                        .applySettings(configuration.getProperties()).build();
                sessionFactory = configuration.buildSessionFactory(serviceRegistry);
            } catch (HibernateException e) {
                throw new RuntimeException(e);
            }
        }
        return sessionFactory;
    }

    public static void shutDown() {
        getSessionFactory().close();
    }
}