package com.teampc.dao;

import com.teampc.model.admin.access.UserSession;
import com.teampc.dao.definitions.SubmissionDD;
import com.teampc.model.test.Test;
import com.teampc.model.testtaking.Submission;
import com.teampc.utils.HibernateUtils;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.Session;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toList;
import static org.hibernate.criterion.Restrictions.eq;

@Slf4j
public class SubmissionDAO extends AbstractDAO<Submission, SubmissionDD> {

   private static SubmissionDAO instance = null;

   public static synchronized SubmissionDAO getInstance() {
      if (instance == null) {
         instance = new SubmissionDAO();
      }
      return instance;
   }

   private SubmissionDAO() {
      // add fake stuff

   }

   /**
    pre: test != null

    post: return != null && return.stream().allMatch(submission -> test.equals(submission.getTest()))
    */
   public Collection<Submission> fetchSubmissionsForTest(Test test) {
      log.debug("Fetching all submissions for test: {}", test);
      if (DEBUG) {
         return fakeDB.values().stream().filter(submission -> test.equals(submission.getTest())).collect(toList());
      }
      log.debug("Test's id is: " + test.getId());

      Session session = HibernateUtils.getSessionFactory().openSession();
      try {
         @SuppressWarnings("unchecked")
         Collection<SubmissionDD> submissions =
               session.createCriteria(getEntityClass())
                     .add(eq("test.id", test.getId()))
                     .list();

         log.debug("Submissions with this test id: " + submissions.size());
         return toModel(submissions);
      } finally {
         session.close();
      }
   }

   public List<Submission> fetchMySubmissions() {
      return fetchAll().stream().filter(nextSubmission -> {
         return nextSubmission.getTaker().equals(UserSession.getLoggedInUser());
      }).collect(Collectors.toList());
   }

   @Override
   protected Class<SubmissionDD> getEntityClass() {
      return SubmissionDD.class;
   }

}
