package com.teampc.utils;

import com.teampc.dao.definitions.*;
import com.teampc.dao.definitions.question.*;
import com.teampc.dao.definitions.response.*;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

/**
 * Created by adufrene on 10/26/15.
 *
 */
public class HibernateUtils {
    private static SessionFactory sessionFactory;

    static {
       Configuration cfg = new Configuration()
             .configure();
       sessionFactory = cfg.buildSessionFactory();
    }

    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }
}
