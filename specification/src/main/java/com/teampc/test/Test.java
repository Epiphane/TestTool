package com.teampc.test;

import com.teampc.admin.*;
import com.teampc.questions.*;
import com.teampc.testtaking.*;
import java.util.*;

/**
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
}
