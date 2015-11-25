package com.teampc.dao;

import com.teampc.model.test.Test;
import com.teampc.utils.HibernateUtils;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TestDAO extends AbstractDAO/*<Test>*/ {

   private static final Logger LOG = LoggerFactory.getLogger(TestDAO.class);
   private static final TestDAO INSTANCE = new TestDAO();

   public static TestDAO getInstance() {
      return INSTANCE;
   }

   private TestDAO() {}

   @Override
   protected Class/*<Test>*/ getEntityClass() {
      return Test.class;
   }

   /**
    * Gets all tests assigned to a given student from the database
    */
   public void read() {
      
   }

   /**
    * Inserts a new test into the database
    */
   @Override
   public void insert(Object obj) {
      // dumb spest
      Test newTest = (Test) obj;
//      Session session = HibernateUtils.getSessionFactory().openSession();
//      Transaction transaction = session.beginTransaction();
//
//      try {
         newTest.getName();
         newTest.getStartDate();
         newTest.getEndDate();
         newTest.getCourseName();

//         session.save(newTest);
//
//         session.flush();
//         transaction.commit();
//      } catch(Exception e) {
//         if (transaction != null) {
//            LOG.error("Rolling back insert");
//            transaction.rollback();
//         }
//         throw e;
//      } finally {
//         session.close();
//      }
   }

   /**
    * Find a test by the test id
    */
   public void findById(String id) {
      LOG.info("Find by id: " + id);
   }

   /**
    * Get all Tests for a specified user. User is identified by userId
    */
   public void getAll(String userId) {
      LOG.info("Find test for user: " + userId);
   }

   /**
    * Updates an existing Test
    */
   public void updateTest(Test update) {
      LOG.info("Update test with: " + update.toString());
   }
}
