package com.teampc.model.test;

import com.teampc.dao.HasId;
import com.teampc.model.admin.Teacher;
import com.teampc.model.admin.User;
import com.teampc.model.admin.course.Course;
import com.teampc.model.question.Question;
import com.teampc.model.testtaking.Key;
import com.teampc.model.testtaking.QuestionResponse;
import com.teampc.model.testtaking.Submission;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import javax.persistence.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * A general Test Class
 * @author Thomas Steinke
 *
 */
@Data
@Entity
@Table(name = "TEST")
@EqualsAndHashCode(exclude={"id","course","owner","questions","published"})
@Slf4j
@NoArgsConstructor
public class Test implements HasId {

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

   private List<Question> questions;

   private Key key;

   @Column(name = "published")
   private boolean published;

   public Test(String name, Date startDate, Date endDate, String courseName) {
      this.name = name;
      this.startDate = startDate;
      this.endDate = endDate;
      this.courseName = courseName;

      this.questions = new ArrayList<>();
   }

   /**
    * Get the name of the test
    */
   public String getName() {
      log.info("Getting name of exam: " + name);
      return name;
   }

   /**
    * Get the test start Date
    */
   public Date getStartDate() {
      log.info("Getting startDate: " + startDate);
      return startDate;
   }

   /**
    * Get the test end Date
    */
   public Date getEndDate() {
      log.info("Getting endDate: " + endDate);
      return endDate;
   }

   public int getTimeLimit() {
      log.info("Getting time limit: " + timeLimit);
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
      log.info("Getting courseName: " + courseName);
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

      if(startDate == null || endDate == null) {
         return info;
      }

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
      log.info("Getting questions");
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
      return published;
   }

   /**
   * Returns whether or not the test can be taken home.
   */
   public boolean isTakeHome() {
      return false;
   }

   /**
    * Take test for the given user, creating a minimally instantiated test submission
     */
   public Submission takeTest(User forUser) {
      Submission newSubmission = new Submission();

      newSubmission.setTaker(forUser);
      newSubmission.setTest(this);

      ArrayList<QuestionResponse> responses = new ArrayList<QuestionResponse>();
      questions.forEach(question -> responses.add(null));
      newSubmission.setResponses(responses);

      return newSubmission;
   }

   /**
   * Try to retake this test, if the test can be retaken, return the test
   * instance in a state ready to be taken. Otherwise, return
   * Optional.empty()
   */
   public Optional<Test> retake() {
      return Optional.empty();
   }

   public void removeQuestion(Question question) {
      if(question == null) { return; }
      questions.remove(question);
   }

   public void moveQuestionUp(Question question) {
      if(question == null || !questions.contains(question)) { return; }

      int curIndex = questions.indexOf(question);
      if(curIndex <= 0) { return; } // do nothing if first question
      Collections.swap(questions, curIndex, curIndex - 1);
   }

   public void moveQuestionDown(Question question) {
      if(question == null || !questions.contains(question)) { return; }

      int curIndex = questions.indexOf(question);
      if(curIndex >= questions.size() - 1) { return; } // do nothing if last question
      Collections.swap(questions, curIndex, curIndex + 1);

   }
}
