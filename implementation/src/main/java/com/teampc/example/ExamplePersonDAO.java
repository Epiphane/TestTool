package com.teampc.db;

import com.teampc.example.ExamplePerson;
import com.teampc.utils.HibernateUtils;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.Collection;
import java.util.Collections;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by adufrene on 10/26/15.
 */
public class ExamplePersonDAO {
    private static final Logger log = LoggerFactory.getLogger(ExamplePersonDAO.class);
    private static final ExamplePersonDAO INSTANCE = new ExamplePersonDAO();

    public static ExamplePersonDAO get() {
        return INSTANCE;
    }

    private ExamplePersonDAO() {
    }

    public Collection<ExamplePerson> fetchAll() {
        Session session = HibernateUtils.getSessionFactory().openSession();
        try {
            Criteria examplePersonCriteria = session.createCriteria(ExamplePerson.class);

            @SuppressWarnings("unchecked")
            Collection<ExamplePerson> examplePersons = examplePersonCriteria.list();

            return examplePersons;
        } finally {
            session.close();
        }
    }

    public void insert(ExamplePerson examplePerson) {
        insert(Collections.singleton(examplePerson));
    }

    public void insert(Collection<ExamplePerson> examplePersons) {
        Session session = HibernateUtils.getSessionFactory().openSession();
        Transaction transaction = null;
        try {
            // Need a transaction to actually save to db
            transaction = session.beginTransaction();
            examplePersons.forEach(session::save);
            session.flush();
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                log.error("Rolling back insert");
                transaction.rollback();
            }
            throw e;
        } finally {
            session.close();
        }
    }

    public void remove(ExamplePerson examplePerson) {
        remove(Collections.singleton(examplePerson));
    }

    public void remove(Collection<ExamplePerson> examplePersons) {
        Transaction transaction = null;
        Session session = HibernateUtils.getSessionFactory().openSession();
        try {
            transaction = session.beginTransaction();
            examplePersons.forEach(session::delete);
            session.flush();
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                log.error("Rolling back deletion");
                transaction.rollback();
            }
            throw e;
        } finally {
            session.close();
        }
    }
}
