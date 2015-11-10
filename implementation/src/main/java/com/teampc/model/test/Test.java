package com.teampc.model.test;

import lombok.Data;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.persistence.*;
import java.util.*;
import java.text.*;

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

   @Column(name = "course_name")
   private String courseName;

//   private Teacher owner;
//
//   private ArrayList<Question> questions;
//
   private boolean published;

   public Test(String name, Date startDate, Date endDate, String courseName) {
      this.name = name;
      this.startDate = startDate;
      this.endDate = endDate;
      this.courseName = courseName;
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

   /**
    * Get the name of the course/subject
    * @return
    */
   public String getCourseName() {
      LOG.info("Getting courseName: " + courseName);
      return courseName;
   }

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

//   /**
//    * Reorders the question list by moving the question in position
//    * `from` to position `to`.
//    */
//   public abstract void moveQuestion(int from, int to);
//
//   /**
//    * Return the list of questions.
//    */
//   public abstract List<Question> getQuestions();
//
//   /**
//    * Get the owner of this test
//    */
//   public abstract Teacher getOwner();
//
//   /**
//    * Publish this test
//    */
//   public abstract void publish();
//
//   /**
//    * Returns whether or not the test has been published
//    */
//   public abstract boolean isPublished();
//
//   /**
//    * Returns whether or not the test can be taken home.
//    */
//   public abstract boolean isTakeHome();
//
//   /**
//    * Try to retake this test, if the test can be retaken, return the test
//    * instance in a state ready to be taken. Otherwise, return
//    * <code>Optional.empty()</code>
//    */
//   public abstract Optional<Test> retake();
}
