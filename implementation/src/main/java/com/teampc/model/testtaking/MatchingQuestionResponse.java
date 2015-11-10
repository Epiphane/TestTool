package com.teampc.model.testtaking;

import java.util.Map;

import com.google.common.collect.Maps;
import lombok.AllArgsConstructor;

/**
 * An answer to a matching question.
 * @author Zach Arend
 */
@AllArgsConstructor
public class MatchingQuestionResponse extends QuestionResponse<MatchingQuestionResponse> {
   private Map<String, String> pairings;

   /** {@inheritDoc} */
   @Override
   public boolean isComplete() {
      return true;
   }

   /** {@inheritDoc} */
   @Override
   public void assignPoints(MatchingQuestionResponse questionResponse) {
      Map<String, String> otherPairings = questionResponse.pairings;
      int differences = Maps.difference(pairings, otherPairings).entriesOnlyOnLeft().size();

      // Difference will be mismatched values for both correct and response pairings,
      // meaning there will be double differences
      questionResponse.pointsReceived = pairings.size() - differences;
   }

}
