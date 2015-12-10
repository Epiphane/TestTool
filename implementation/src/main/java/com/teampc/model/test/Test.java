package com.teampc.model.test;

import com.google.common.collect.Lists;
import com.teampc.dao.HasId;
import com.teampc.dao.definitions.TestDD;
import com.teampc.dao.definitions.TestQuestionDD;
import com.teampc.model.Model;
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

import java.text.DateFormat;
import java.text.SimpleDateFormat;

import java.util.*;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toList;

/**
 * A general Test Class
 * @author Thomas Steinke
 *
 */
@Data
@NoArgsConstructor
@EqualsAndHashCode(exclude={"id","course","owner","questions","published"})
@Slf4j
public class Test implements Model<TestDD> {

   private Integer id;

   private String name;

   private Date startDate;

   private Date endDate;

   private int timeLimit;

   private Course course;

   private Teacher owner;

   private List<Question> questions;

   private boolean published;

   public Test(String name, Date startDate, Date endDate, String courseName) {
      this.name = name;
      this.startDate = startDate;
      this.endDate = endDate;

      this.questions = new ArrayList<>();
   }

    /**
     * Copy constructor
     */
   public Test(Test test) {
      this.id = test.id;
      this.name = test.name;
      this.startDate = test.startDate != null ? new Date(test.startDate.getTime()) : null;
      this.endDate = test.endDate != null ? new Date(test.endDate.getTime()) : null;
      this.timeLimit = test.timeLimit;
      this.courseName = test.courseName;
      this.course = new Course(test.course);
      this.owner = new Teacher(test.owner);
      this.questions = Lists.newArrayListWithExpectedSize(test.questions.size());
      test.questions.forEach(question -> this.questions.add(new Question<>(question)));
      this.published = test.published;
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
    *
      pre: startDate != null && endDate != null
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
      if (course != null) {
         log.info("Getting courseName: " + course.getTitle());
         return course.getTitle();
      }
      return "";
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

      String info = (course == null ? "<null course>" : course.getTitle()) + " " + name + " - ";

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
    *
      pre: forUser != null && this.isOpen()

      post: result.getResponses() != null &&
            result.getUser() == forUser
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

   /**
    * Remove a question from the test
    *
      pre: questions != null && questions.size() > 0
            && question != null && questions.indexOf(question) >= 0
    */
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

   /**
    * generates a key for this test
    *
      pre: !this.isOpen()
    */
   public Key generateKey() {
      Key key = new Key();
      key.setTest(this);

      ArrayList<QuestionResponse> correctResponses = new ArrayList<>();
      for (Question nextQuestion : questions) {
         correctResponses.add(nextQuestion.getCorrectAnswer());
      }

      key.setResponses(correctResponses);

      return key;
   }

   public TestDD asEntity() {
      TestDD test = new TestDD();
      test.setCourse(course != null ? course.asEntity() : null);
      test.setId(id);
      test.setTitle(name);
      test.setStartDate(new java.sql.Date(startDate.getTime()));
      test.setEndDate(new java.sql.Date(endDate.getTime()));
      test.setTimeAllowed(timeLimit);
      test.setQuestions(new HashSet<TestQuestionDD>());
      test.setPublished(published ? 1 : 0);

      for (Question question : questions) {
         //create a new TestQuestionDD object and add it to questions
         TestQuestionDD testQuestion = new TestQuestionDD();
         testQuestion.setTest(test);
         testQuestion.setQuestion(question.asEntity());
         testQuestion.setRank(questions.indexOf(question));
         test.getQuestions().add(testQuestion);
      }

      return test;
   }
}
