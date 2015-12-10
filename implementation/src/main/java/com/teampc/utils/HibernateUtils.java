package com.teampc.utils;

import com.teampc.model.test.Test;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

/**
 * Created by adufrene on 10/26/15.
 *
 */
public class HibernateUtils {
    private static SessionFactory sessionFactory = new Configuration()
            .configure()
            .addAnnotatedClass(Test.class)
            .buildSessionFactory();

    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }
}
