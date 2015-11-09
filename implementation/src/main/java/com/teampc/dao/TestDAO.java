package com.teampc.dao;

import com.teampc.model.test.Test;
import com.teampc.utils.HibernateUtils;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TestDAO {

   private static final Logger LOG = LoggerFactory.getLogger(TestDAO.class);
   private static final TestDAO INSTANCE = new TestDAO();

   public static TestDAO getInstance() {
      return INSTANCE;
   }

   private TestDAO() {}

   /**
    * Inserts a new test into the database
    */
   public void insert(Test newTest) {
      Session session = HibernateUtils.getSessionFactory().openSession();
      Transaction transaction = session.beginTransaction();

      try {
         session.save(newTest);

         session.flush();
         transaction.commit();
      } catch(Exception e) {
         if (transaction != null) {
            LOG.error("Rolling back insert");
            transaction.rollback();
         }
         throw e;
      } finally {
         session.close();
      }
   }
}
