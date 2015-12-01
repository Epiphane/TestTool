package com.teampc.dao;

import com.teampc.model.test.Test;
import com.teampc.utils.HibernateUtils;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TestDAO extends AbstractDAO<Test> {

   private static final Logger LOG = LoggerFactory.getLogger(TestDAO.class);
   private static final TestDAO INSTANCE = new TestDAO();

   public static TestDAO getInstance() {
      return INSTANCE;
   }

   private TestDAO() {}

   @Override
   protected Class<Test> getEntityClass() {
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
   public void insert(Test newTest) {
      super.insert(newTest);
      LOG.info("Inserting a new test");

      newTest.getName();
      newTest.getStartDate();
      newTest.getEndDate();
      newTest.getCourseName();
   }

   /**
    * Find a test by the test id
    */
   public void findById(String id) {
      LOG.info("Find by id: " + id);
   }

   /**
    * Updates an existing Test
    */
   public void updateTest(Test update) {
      LOG.info("Update test with: " + update.toString());
   }
}
