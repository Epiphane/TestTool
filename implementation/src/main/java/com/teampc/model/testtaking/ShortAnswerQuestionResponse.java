package com.teampc.model.testtaking;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

/**
 * A response to a short answer question.
 * @author Zach Arend
 */
public class ShortAnswerQuestionResponse extends QuestionResponse/*<ShortAnswerQuestionResponse>*/ {

   @Getter
   @Setter
   private String answer;

   /** {@inheritDoc} */
   @Override
   public boolean isComplete() {
      return true;
   }

   /** {@inheritDoc} */
   @Override
   public void assignPoints(/*ShortAnswerQuestionResponse*/ QuestionResponse questionResponse) {
      // need manual grading
   }

}
