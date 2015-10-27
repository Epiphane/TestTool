package com.teampc.db;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.teampc.example.ExamplePerson;

/**
 * Created by adufrene on 10/26/15.
 */
public class DBUtils {
    public static SessionFactory sessionFactory = new Configuration()
            .configure()
            .addAnnotatedClass(ExamplePerson.class)
            .buildSessionFactory();
}
