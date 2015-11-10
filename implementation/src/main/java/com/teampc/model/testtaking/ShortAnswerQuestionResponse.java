package com.teampc.model.testtaking;

import lombok.AllArgsConstructor;

/**
 * A response to a short answer question.
 * @author Zach Arend
 */
@AllArgsConstructor
public class ShortAnswerQuestionResponse extends QuestionResponse<ShortAnswerQuestionResponse> {
   private String answer;

   /** {@inheritDoc} */
   @Override
   public boolean isComplete() {
      return true;
   }

   /** {@inheritDoc} */
   @Override
   public void assignPoints(ShortAnswerQuestionResponse questionResponse) {
      // need manual grading
   }

}
