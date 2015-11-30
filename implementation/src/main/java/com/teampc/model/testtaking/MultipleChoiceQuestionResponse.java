package com.teampc.model.testtaking;

import lombok.AllArgsConstructor;

/**
 * A response to a multiple choice question question.
 * @author Zach Arend
 */
@AllArgsConstructor
public class MultipleChoiceQuestionResponse extends QuestionResponse<MultipleChoiceQuestionResponse> {
    // an int representing which answer was chosen. 1 for A, 2 for B, etc
    private int answer;

   /** {@inheritDoc} */
   @Override
   public boolean isComplete() {
      return true;
   }

   /** {@inheritDoc} */
   @Override
   public void assignPoints(MultipleChoiceQuestionResponse questionResponse) {
      questionResponse.pointsReceived = answer == questionResponse.answer ? 1 : 0;
   }

   @Override
   public String toString() {
      return Character.toString(Character.toChars('a' + answer)[0]);
   }
}
