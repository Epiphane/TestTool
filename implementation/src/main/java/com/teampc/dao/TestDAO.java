package com.teampc.dao;

import com.teampc.model.test.Test;
import com.teampc.utils.HibernateUtils;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;
import com.teampc.model.test.*;

public class TestDAO extends AbstractDAO<Test> {

   private static final Logger LOG = LoggerFactory.getLogger(TestDAO.class);
   private static final TestDAO INSTANCE = new TestDAO();

   public static TestDAO getInstance() {
      return INSTANCE;
   }

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
    * Find a test by the test id
    */
   public void findById(int id) {
      LOG.info("Find by id: " + id);
   }

}
