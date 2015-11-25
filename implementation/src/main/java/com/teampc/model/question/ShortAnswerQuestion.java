package com.teampc.model.question;

import com.teampc.model.testtaking.ShortAnswerQuestionResponse;

/**
 * Created by adufrene on 11/9/15.
 */
public class ShortAnswerQuestion extends Question/*<ShortAnswerQuestionResponse>*/ {
   @Override
   public QuestionTypeName getTypeName() {
      return QuestionTypeName.SHORT_ANSWER;
   }
}
