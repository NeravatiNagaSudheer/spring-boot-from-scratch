package com.example.hibernate.util;

import com.example.hibernate.config.HibernateConfiguration;
import org.hibernate.SessionFactory;

public class HibernateUtil {

    private static final SessionFactory sessionFactory =
            HibernateConfiguration
                    .getConfiguration()
                    .buildSessionFactory();

    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public static void shutdown() {
        sessionFactory.close();
    }
}
