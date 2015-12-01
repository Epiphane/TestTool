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
   private MatchType matchType;

   /** {@inheritDoc} */
   @Override
   public boolean isComplete() {
      return true;
   }

   /** {@inheritDoc} */
   @Override
   public void assignPoints(ShortAnswerQuestionResponse questionResponse, int maxPoints) {
      // need manual grading
      questionResponse.pointsReceived = answer.equals(questionResponse) ? maxPoints : 0;
   }

   @Override
   public String toString() {
      return answer;
   }

   public enum MatchType {
      EXACTLY,
      ANY,
      ALL
   }
}
