package com.teampc.testtaking;

import com.teampc.admin.*;
import com.teampc.test.*;
import java.util.*;

/**
 * An instance of an in-progress test being taken
 * @author Thomas Steinke
 *
 */
public abstract class Submission {

   private Test test;

   private User taker;

   private ArrayList<QuestionResponse> responses;

   private boolean complete;

   private float grade;

   /**
    * Return whether the test is complete or not
    */
   public abstract boolean isComplete();

   /**
    * Determine how many questions have been completed so far.
    */
   public abstract int questionsCompleted();

   /**
    * Grade the test against a teacher's key
    */
   public abstract void gradeTest(Key correctAnswers);

   /**
    * Return the completed Test's grade
    */
   public abstract void getGrade();

}
