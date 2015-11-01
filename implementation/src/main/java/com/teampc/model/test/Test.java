package com.teampc.model.test;

import com.teampc.model.admin.*;
import com.teampc.model.question.*;
import com.teampc.model.testtaking.*;
import java.util.*;

/**
 * A general Test Class
 * @author Thomas Steinke
 *
 */
public abstract class Test {
   
   private Teacher owner;

   private ArrayList<Question> questions;

   private boolean published;

   /**
    * Reorders the question list by moving the question in position
    * `from` to position `to`.
    */
   public abstract void moveQuestion(int from, int to);

   /**
    * Return the list of questions.
    */
   public abstract List<Question> getQuestions();

   /**
    * Get the owner of this test
    */
   public abstract Teacher getOwner();

   /**
    * Publish this test
    */
   public abstract void publish();

   /**
    * Returns whether or not the test has been published
    */
   public abstract boolean isPublished();

   /**
    * Returns whether or not the test can be taken home.
    */
   public abstract boolean isTakeHome();

   /**
    * Try to retake this test, if the test can be retaken, return the test
    * instance in a state ready to be taken. Otherwise, return
    * <code>Optional.empty()</code>
    */
   public abstract Optional<Test> retake();
}
