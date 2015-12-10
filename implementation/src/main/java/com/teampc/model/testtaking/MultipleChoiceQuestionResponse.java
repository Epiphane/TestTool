package com.teampc.model.testtaking;

import lombok.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * MultiplechoiceQuestionResponse represents the answers to a MC Question
 * @author Zach Arend
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class MultipleChoiceQuestionResponse extends QuestionResponse<MultipleChoiceQuestionResponse> {
   // an int representing which answer was chosen. 1 for A, 2 for B, etc
   private int answer;
   private List<String> choices;

    /**
     * Copy constructor
     */
   public MultipleChoiceQuestionResponse(MultipleChoiceQuestionResponse questionResponse) {
      this.answer = questionResponse.answer;
      this.choices = new ArrayList<>(questionResponse.choices);
      finishCopy(questionResponse);
   }

   /** {@inheritDoc} */
   @Override
   public boolean isComplete() {
      return true;
   }

   /** {@inheritDoc} */
   @Override
   public void assignPoints(MultipleChoiceQuestionResponse questionResponse, int maxPoints) {
      questionResponse.pointsReceived = answer == questionResponse.answer ? 1 : 0;
   }

   /** {@inheritDoc} */
   @Override
   public MultipleChoiceQuestionResponse copy() {
      return null;
   }

   @Override
   public String toString() {
      if (choices == null) {
         return "(No Choices)";
      }
      return answer < 0 || answer >= choices.size() ? "(No Answer)" : choices.get(answer);
   }

   /** Student version of response */
   public static MultipleChoiceQuestionResponse studentResponse(int answer) {
      return new MultipleChoiceQuestionResponse(answer, Collections.emptyList());
   }
}
