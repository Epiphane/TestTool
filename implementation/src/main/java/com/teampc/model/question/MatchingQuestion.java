package com.teampc.model.question;

import com.teampc.model.testtaking.MatchingQuestionResponse;

/**
 * This class represents a matching question.
 * @author David Ellison
 */
public class MatchingQuestion extends Question<MatchingQuestionResponse> {

   /** {@inheritDoc} */
   @Override
   public QuestionTypeName getTypeName() {
      return QuestionTypeName.MATCHING;
   }

   //Correct answer is a Map containing all the correct pairings of answers
   //But that's inside the QuestionResponse.
}
