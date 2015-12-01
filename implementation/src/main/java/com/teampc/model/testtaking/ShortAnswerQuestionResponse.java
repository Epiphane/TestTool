package com.teampc.model.testtaking;

import lombok.AllArgsConstructor;

/**
 * A response to a short answer question.
 * @author Zach Arend
 */
@AllArgsConstructor
public class ShortAnswerQuestionResponse extends QuestionResponse<ShortAnswerQuestionResponse> {
   private String answer;
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
