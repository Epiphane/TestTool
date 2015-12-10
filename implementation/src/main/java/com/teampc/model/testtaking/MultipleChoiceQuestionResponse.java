package com.teampc.model.testtaking;

import com.teampc.dao.definitions.question.MultipleChoiceOptionDD;
import com.teampc.dao.definitions.question.QuestionDD;
import com.teampc.dao.definitions.response.MultipleChoiceQuestionResponseDD;
import lombok.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;

/**
 * A response to a multiple choice question question.
 * @author Zach Arend
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class MultipleChoiceQuestionResponse extends QuestionResponse<MultipleChoiceQuestionResponse> {
   // an int representing which answer was chosen. 1 for A, 2 for B, etc
   private int answer;
   //A list of Strings representing the possible choices.
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

   /**
   @Override
   public String toString() {
      if (choices == null) {
         return "(No Choices)";
      }
      return answer < 0 || answer >= choices.size() ? "(No Answer)" : choices.get(answer);
   }
   */

   /** Student version of response */
   public static MultipleChoiceQuestionResponse studentResponse(int answer) {
      return new MultipleChoiceQuestionResponse(answer, Collections.emptyList());
   }

   public MultipleChoiceQuestionResponseDD asEntity() {
      MultipleChoiceQuestionResponseDD response = new MultipleChoiceQuestionResponseDD();

      response.setId(id);
      response.setPointsReceived(pointsReceived);

      response.setResponseRank(answer);
      response.setChoices(new HashSet<>());
      if (question != null) {
         response.setQuestion(question.asEntity());
      }

      for (int i = 0; i < choices.size(); i++) {
         MultipleChoiceOptionDD option = new MultipleChoiceOptionDD();
         option.setRank(i);
         option.setText(choices.get(i));
         response.getChoices().add(option);
      }

      return response;
   }

   public MultipleChoiceQuestionResponseDD asEntity(QuestionDD q) {
      MultipleChoiceQuestionResponseDD response = new MultipleChoiceQuestionResponseDD();

      response.setId(id);
      response.setPointsReceived(pointsReceived);

      response.setResponseRank(answer);
      response.setChoices(new HashSet<>());

      response.setQuestion(q);

      for (int i = 0; i < choices.size(); i++) {
         MultipleChoiceOptionDD option = new MultipleChoiceOptionDD();
         option.setRank(i);
         option.setText(choices.get(i));
         response.getChoices().add(option);
      }

      return response;
   }
}
