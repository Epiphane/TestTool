package com.teampc.model.testtaking;

import com.teampc.model.admin.*;
import com.teampc.model.test.*;
import java.util.*;

import com.teampc.model.automation.*;

import lombok.Data;

/**
 * An instance of an in-progress test being taken
 * @author Thomas Steinke
 *
 */
@Data
public class Submission {

   private Test test;

   private User taker;

   public ArrayList<QuestionResponse> responses;

   private boolean complete;

   private float grade;

   /**
    * Return whether the test is complete or not
    */
   public boolean isComplete() { 
      return complete;
   }

   /**
    * Determine how many questions have been completed so far.
    */
   public int questionsCompleted() {
      return 0;
   }

   public int getNextUnansweredQuestion() {
      return 0;
   }

   /**
    * Grade the test against a teacher's key
    */
   public void gradeTest(Key correctAnswers) {
      TestGrader.gradeTest(this, correctAnswers);

      this.grade = 0;

      for (QuestionResponse q: responses) {
         grade += q.getPointsReceived();
      }
   }

}
