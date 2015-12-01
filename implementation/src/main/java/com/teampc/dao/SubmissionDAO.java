package com.teampc.dao;

import com.teampc.model.testtaking.Submission;
import com.teampc.model.test.Test;
import com.teampc.utils.HibernateUtils;

import org.hibernate.Session;

import java.util.Collection;

import static org.hibernate.criterion.Restrictions.eq;
import static java.util.stream.Collectors.toList;

public class SubmissionDAO extends AbstractDAO<Submission> {

   private static SubmissionDAO instance = null;

   public static synchronized SubmissionDAO getInstance() {
      if (instance == null) {
         instance = new SubmissionDAO();
      }
      return instance;
   }

   private SubmissionDAO() {}

   public Collection<Submission> fetchSubmissionsForTest(Test test) {
      if (DEBUG) {
         return debugCollection.stream().filter(submission -> test.equals(submission.getTest())).collect(toList());
      }

      Session session = HibernateUtils.getSessionFactory().openSession();
      try {
         @SuppressWarnings("unchecked")
         Collection<Submission> submissions = session.createCriteria(getEntityClass())
            .add(eq("test", test))
            .list();

         return submissions;
      } finally {
         session.close();
      }
   }

   @Override
   protected Class<Submission> getEntityClass() {
      return Submission.class;
   }

}
