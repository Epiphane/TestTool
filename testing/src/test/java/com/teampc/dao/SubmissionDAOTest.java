package com.teampc.dao;

import com.teampc.model.admin.Student;
import com.teampc.model.testtaking.Submission;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.Collection;
import java.util.Collections;
import java.util.Date;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * Created by adufrene on 12/9/15.
 */
public class SubmissionDAOTest {

   private Collection<Submission> dbState;
   private SubmissionDAO submissionDAO = SubmissionDAO.getInstance();

   @Before
   public void setup() {
      // Save and clear
      dbState = submissionDAO.fetchAll();
      submissionDAO.delete(submissionDAO.fetchAll());
   }

   @After
   public void cleanup() {
      // Reset db
      submissionDAO.delete(submissionDAO.fetchAll());
      submissionDAO.insert(dbState);
   }

   @Test
   public void testSubmissionsForTest() {
      com.teampc.model.test.Test test = new com.teampc.model.test.Test("fake test", new Date(), new Date(), "fake course");
      Student fakeStudent = new Student("user", "first", "last");
      Submission fakeSubmission = new Submission(test, fakeStudent, Collections.emptyList(), true);
      Submission fakeSubmission2 = new Submission(fakeSubmission);

      // Make sure no submissions for test
      assertEquals(0, submissionDAO.fetchSubmissionsForTest(test).size());

      // Insert one and check
      submissionDAO.insert(fakeSubmission);
      Collection<Submission> fetchedSubmissions = submissionDAO.fetchSubmissionsForTest(test);
      assertEquals(1, fetchedSubmissions.size());
      assertTrue(fetchedSubmissions.contains(fakeSubmission));

      // Insert second and check
      submissionDAO.insert(fakeSubmission2);
      fetchedSubmissions = submissionDAO.fetchSubmissionsForTest(test);
      assertEquals(2, fetchedSubmissions.size());
      assertTrue(fetchedSubmissions.contains(fakeSubmission));
      assertTrue(fetchedSubmissions.contains(fakeSubmission2));

      // Remove one and check
      submissionDAO.delete(fakeSubmission);
      fetchedSubmissions = submissionDAO.fetchSubmissionsForTest(test);
      assertEquals(1, fetchedSubmissions.size());
      fakeSubmission.setId(fakeSubmission.getId() + 1);
      assertTrue(fetchedSubmissions.contains(fakeSubmission2));
   }
}
