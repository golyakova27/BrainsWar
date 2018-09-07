package com.golyakova.brainwar.utils;

import com.golyakova.brainwar.models.*;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;


public class HibernateSessionFactoryUtil {
    private static SessionFactory sessionFactory;

    private HibernateSessionFactoryUtil() {

    }

    public static SessionFactory getSessionFactory() {
        if (sessionFactory == null) {
            try {
                // в следующей строке зачитывается hibernate.cfg.xml
                Configuration configuration = new Configuration().configure();
                configuration.addAnnotatedClass(User.class);
                configuration.addAnnotatedClass(Question.class);
                configuration.addAnnotatedClass(Questionart.class);
                configuration.addAnnotatedClass(Questionmusic.class);
                configuration.addAnnotatedClass(Questionnature.class);
                StandardServiceRegistryBuilder builder =
                        new StandardServiceRegistryBuilder().applySettings(configuration.getProperties());
                sessionFactory = configuration.buildSessionFactory(builder.build());
            }
            catch (Exception e) {
                System.out.println("SessionHibernateException " + e);
            }
        }
        return sessionFactory;
    }
}
