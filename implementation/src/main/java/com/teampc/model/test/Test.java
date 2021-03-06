package com.teampc.model.test;

import com.google.common.collect.Lists;
import com.teampc.dao.HasId;
import com.teampc.model.admin.Teacher;
import com.teampc.model.admin.User;
import com.teampc.model.admin.course.Course;
import com.teampc.model.automation.TestGrader;
import com.teampc.model.question.Question;
import com.teampc.model.testtaking.Key;
import com.teampc.model.testtaking.QuestionResponse;
import com.teampc.model.testtaking.Submission;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import javax.persistence.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toList;

/**
 * Test represents all test objects handled by the application.
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

   @Getter
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
    * @return The name of the test
    */
   public String getName() {
      log.info("Getting name of exam: " + name);
      return name;
   }

   /**
    * Get the test start Date
    * @return The start date of the test
    */
   public Date getStartDate() {
      log.info("Getting startDate: " + startDate);
      return startDate;
   }

   /**
    * Get the test end Date
    * @return The end date of the test
    */
   public Date getEndDate() {
      log.info("Getting endDate: " + endDate);
      return endDate;
   }

   /**
    * Get the time limit of the test
    * @return the time limit of the test
    */
   public int getTimeLimit() {
      log.info("Getting time limit: " + timeLimit);
      return timeLimit;
   }

   /**
    * Determines whether the test is available to be taken
    *@return Whether the test is available to be taken
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
      log.info("Getting courseName: " + courseName);
      return courseName;
   }

   /**
    * Assess and set the point value on a response to this question.
    *
      pre: startDate != null && endDate != null
    *
    * @return Get the string representation of this test
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
    * * Assess and set the point value on a response to this question.
    * Reorders the question list by moving the question in position
    * from to position to.
    *
    pre: from > 0 && from < questions.size() &&
    to > 0 && to < questions.size() &&
    from != to
    * @param from Where the question is in the test
    * @param to Where we want the question to be
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

      @param forUser The user who is currently taking the test
      @return The submission of the test by the user.
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

     @param question The question to remove from the test
    */
   public void removeQuestion(Question question) {
      if(question == null) { return; }
      questions.remove(question);
   }

   /**
    *
    * pre: questions != null
    *
    * post: question == null || (questions.indexOf(question) == 0 || questions'.indexOf(question) == questions.indexOf(question) - 1) 
    *
    * @param question the question whose position we want to move up in the test
    */
   public void moveQuestionUp(Question question) {
      if(question == null || !questions.contains(question)) { return; }

      int curIndex = questions.indexOf(question);
      if(curIndex <= 0) { return; } // do nothing if first question
      Collections.swap(questions, curIndex, curIndex - 1);
   }

   /**
    * @param question the question whose position we want to move down in the test
    * pre: questions != null
    *
    * post: question == null || (questions.indexOf(question) == questions.size() - 1 || questions'.indexOf(question) == questions.indexOf(question) + 1)
    */
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

      post: TestGrader.gradeTest(key, key) && key.grade == 100.0% && !this.isOpen()

    @return The key to the test
    */
   public Key generateKey() {
      Key key = new Key();
      key.setTest(this);

      ArrayList<QuestionResponse> correctResponses = new ArrayList<>();
      for (Question nextQuestion: questions) {
         correctResponses.add(nextQuestion.getCorrectAnswer());
      }

      key.setResponses(correctResponses);

      this.key = key;

      return key;
   }
}
