package com.teampc.dao;

import com.teampc.dao.definitions.TestDD;
import com.teampc.model.test.Test;
import com.teampc.utils.HibernateUtils;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;
import com.teampc.model.test.*;

/**
 * TestDAO provides access to the Test storage. This class provides create, read, update, delete operations
 * for Tests. This class is expected to be singleton.
 *
 * @author Jameson Li (jrli@calpoly.edu)
 */

public class TestDAO extends AbstractDAO<Test, TestDD> {
   /** Provides logging for TestDAO **/
   private static final Logger LOG = LoggerFactory.getLogger(TestDAO.class);

   /** Create a single instance of TestDAO **/
   private static final TestDAO INSTANCE = new TestDAO();

   /**
    * Return an instance of TestDAO
    * Only one instance should be created
    */
   public static TestDAO getInstance() {
      return INSTANCE;
   }

   private TestDAO() {}

   @Override
   /**
    * Provides the class entity
    */
   protected Class<TestDD> getEntityClass() {
      return TestDD.class;
   }

   /**
    * Gets all tests assigned to a given student from the database
    */
   public void read() {

   }

   /**
    * Inserts a new test into the database
    */
   /*
   @Override
   public void insert(Test newTest) {
      //Create a TestDD object.
      //Create TestQuestionDD rows corresponding to the questions
      //Remove all TestQuestionDD rows for that test.
      //
      super.insert(newTest);

      newTest.getName();
      newTest.getStartDate();
      newTest.getEndDate();
      newTest.getCourseName();
   }*/

   /**
    * Find a test by the test id
    */
   public void findById(int id) {
      LOG.info("Find by id: " + id);
   }

}
