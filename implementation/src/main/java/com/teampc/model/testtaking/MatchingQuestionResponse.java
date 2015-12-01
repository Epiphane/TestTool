package com.teampc.model.testtaking;

import java.util.HashMap;
import java.util.Set;
import java.util.HashSet;
import java.util.Map;

import com.google.common.collect.Maps;
import com.teampc.dao.definitions.response.MatchingQuestionPairDD;
import com.teampc.dao.definitions.response.MatchingQuestionResponseDD;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

/**
 * An answer to a matching question.
 * @author Zach Arend
 */

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class MatchingQuestionResponse extends QuestionResponse<MatchingQuestionResponse> {
   @Getter
   private Map<String, String> pairings;

    /**
     * copy constructor
     */
   public MatchingQuestionResponse(MatchingQuestionResponse questionResponse) {
      this.pairings = new HashMap<>(pairings);
      finishCopy(questionResponse);
   }

   /** {@inheritDoc} */
   @Override
   public boolean isComplete() {
      return true;
   }

   /**
    *
    *
   pre: questionResponse != null && questionResponse.isComplete()
    *
    *
   post: questionResponse.pointsReceived >= 0 && questionResponse.pointsReceived <= maxPoints
    * @param maxPoints
    */
   @Override
   public void assignPoints(MatchingQuestionResponse questionResponse, int maxPoints) {
      Map<String, String> otherPairings = questionResponse.pairings;
      int differences = Maps.difference(pairings, otherPairings).entriesOnlyOnLeft().size();

      // Difference will be mismatched values for both correct and response pairings,
      // meaning there will be double differences
      questionResponse.pointsReceived = pairings.size() - differences;
   }

   /**
    * {@inheritDoc}
     */
   @Override
   public MatchingQuestionResponse copy() {
      return new MatchingQuestionResponse(this);
   }

   @Override
   public String toString() {
      StringBuilder sb = new StringBuilder();
      pairings.forEach((key, value) -> sb.append(", ").append(key).append(" => ").append(value));
      if (sb.length() > 2) {
         sb.deleteCharAt(0).deleteCharAt(0);
      }
      return sb.toString();
   }

   public MatchingQuestionResponseDD asEntity() {
      MatchingQuestionResponseDD response = new MatchingQuestionResponseDD();

      response.setId(id);
      response.setPointsReceived(pointsReceived);
      response.setPairs(new HashSet<>());

      //Ensure that the pairs are cleared from the table
      //and re-saved on update and insert.
      //FOR SOME REASON, THIS ISN'T SAVING NON-CORRECT PAIRS.
      for (Map.Entry<String, String> entry : pairings.entrySet()) {
         MatchingQuestionPairDD pair = new MatchingQuestionPairDD();
         pair.setPrompt(entry.getKey());
         pair.setResponse(entry.getValue());

         pair.setQuestion(response);
         response.getPairs().add(pair);
      }

      return response;
   }
}
