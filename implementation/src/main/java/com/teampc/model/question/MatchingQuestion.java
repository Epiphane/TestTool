package com.teampc.model.question;

import com.teampc.model.testtaking.QuestionResponse;

/**
 * This class represents a matching question.
 * @author David Ellison
 */
public class MatchingQuestion extends Question {

   @Override
   public void grade(QuestionResponse response) {

   }

   @Override
   public QuestionTypeName getTypeName() {
      return null;
   }
   //Correct answer is a Map containing all the correct pairings of answers
   //But that's inside the QuestionResponse.
}
