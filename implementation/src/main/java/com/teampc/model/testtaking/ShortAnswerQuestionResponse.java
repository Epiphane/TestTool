package com.teampc.model.testtaking;

import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

/**
 * A response to a short answer question.
 * @author Zach Arend
 */

@NoArgsConstructor
@AllArgsConstructor
public class ShortAnswerQuestionResponse extends QuestionResponse<ShortAnswerQuestionResponse> {
   @Getter
   @Setter
   private String answer = "";

   /** {@inheritDoc} */
   @Override
   public boolean isComplete() {
      return true;
   }

   /** {@inheritDoc} */
   @Override
   public void assignPoints(ShortAnswerQuestionResponse questionResponse) {
      questionResponse.pointsReceived = answer.equals(questionResponse.answer) ? 1 : 0;
   }

   @Override
   public String toString() {
      return answer;
   }
}
