package com.teampc.model.test;

import lombok.Data;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.persistence.*;
import java.util.*;
import java.text.*;

import com.teampc.model.admin.*;
import com.teampc.model.admin.course.Course;
import com.teampc.model.question.*;
import com.teampc.model.testtaking.*;

/**
 * A general Test Class
 * @author Thomas Steinke
 *
 */
@Data
@Entity
@Table(name = "TEST")
public class Test {
   private static final Logger LOG = LoggerFactory.getLogger(Test.class);

   @Id
   @GeneratedValue(strategy = GenerationType.AUTO)
   @Column(name = "id")
   private int id;

   @Column(name = "name")
   private String name;

   @Column(name = "start_date")
   private Date startDate;

   @Column(name = "end_date")
   private Date endDate;

   @Column(name = "time_limit")
   private int timeLimit;

   @Column(name = "course_name")
   private String courseName;

   private Course course;

   private Teacher owner;

   private ArrayList<Question> questions;

   private boolean published;

   public Test(String name, Date startDate, Date endDate, String courseName) {
      this.name = name;
      this.startDate = startDate;
      this.endDate = endDate;
      this.courseName = courseName;

      this.questions = new ArrayList<Question>();
   }

   public Test() {

   }

   /**
    * Get the name of the test
    */
   public String getName() {
      LOG.info("Getting name of exam: " + name);
      return name;
   }

   /**
    * Get the test start Date
    */
   public Date getStartDate() {
      LOG.info("Getting startDate: " + startDate);
      return startDate;
   }

   /**
    * Get the test end Date
    */
   public Date getEndDate() {
      LOG.info("Getting endDate: " + endDate);
      return endDate;
   }

   public int getTimeLimit() {
      LOG.info("Getting time limit: " + timeLimit);
      return timeLimit;
   }

   /**
    * Determines whether the test is available to be taken
    */
   public boolean isOpen() {
      if (!published) {
         return false; // Not published yet!
      }

      Date today = new Date();

      if (today.compareTo(startDate) < 0) {
         return false; // Not open yet
      }
      else if (today.compareTo(endDate) < 0) {
         return true; // Open!
      }
      else {
         return false; // Closed already
      }
   }

   /**
    * Get the name of the course/subject
    */
   public String getCourseName() {
      LOG.info("Getting courseName: " + courseName);
      return courseName;
   }

   /**
    * Assess and set the point value on a response to this question.
    *
      pre: startDate != null && endDate != null
    *
    */
   public String toString() {
      DateFormat df = new SimpleDateFormat("EE MMM d, YYYY");
      Date today = new Date();

      String info = courseName + " " + name + " - ";

      if (today.compareTo(startDate) < 0) {
         return info + " opens on " + df.format(startDate);
      }
      else if (today.compareTo(endDate) < 0) {
         return info + " closes on " + df.format(endDate);
      }
      else {
         return info + " closed on " + df.format(endDate);
      }
   }

   /**
    * Assess and set the point value on a response to this question.
    * Reorders the question list by moving the question in position
    * from to position to.
    *
      pre: from > 0 && from < questions.size() &&
         to > 0 && to < questions.size() &&
         from != to
    */
   public void moveQuestion(int from, int to) {

   }

   /**
    * Return the list of questions.
    */
   public List<Question> getQuestions() {
      LOG.info("Getting questions");
      return questions;
   }

   /**
    * Get the owner of this test
    */
   public Teacher getOwner() {
      return owner;
   }

   /**
    *
    * Publish this test
    *
          pre: !published
    *
          post: published
    *
    */
   public void publish() {
      assert !published;

      published = true;
   }

   /**
   * Returns whether or not the test has been published
   */
   public boolean isPublished() {
      return false;
   }

   /**
   * Returns whether or not the test can be taken home.
   */
   public boolean isTakeHome() {
      return false;
   }

   public Submission takeTest(User forUser) {
      Submission newSubmission = new Submission();

      newSubmission.setTaker(forUser);
      newSubmission.setTest(this);

      ArrayList<QuestionResponse> responseShells = new ArrayList<QuestionResponse>();

      for (int qNum = 0; qNum < questions.size(); qNum ++) {
         responseShells.add(questions.get(qNum).createResponse());
      }

      newSubmission.setResponses(responseShells);

      return newSubmission;
   }

   /**
   * Try to retake this test, if the test can be retaken, return the test
   * instance in a state ready to be taken. Otherwise, return
   * Optional.empty()
   */
   public Optional<Test> retake() {
      return null;
   }
}
