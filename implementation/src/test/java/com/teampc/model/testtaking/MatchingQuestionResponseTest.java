package com.teampc.model.testtaking;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

/**
 * tests for MatchingQuestionResponse class.
 */
public class MatchingQuestionResponseTest {
   @Test
   public void testToString() {
      Map<String, String> pairs = new HashMap<>();
      pairs.put("a", "alpha");
      pairs.put("b", "bravo");
      MatchingQuestionResponse response = new MatchingQuestionResponse(pairs);
      assertEquals("a => alpha, b => bravo", response.toString());
   }

   @Test
   public void testAssignPoints() {
      Map<String, String> keyPairs = new HashMap<>();
      keyPairs.put("a", "alpha");
      keyPairs.put("b", "bravo");
      keyPairs.put("c", "charlie");
      MatchingQuestionResponse correctResponse = new MatchingQuestionResponse(keyPairs);

      Map<String, String> wrongPairs = new HashMap<>();
      wrongPairs.put("a", "bravo");
      wrongPairs.put("b", "charlie");
      wrongPairs.put("c", "alpha");
      MatchingQuestionResponse wrongResponse = new MatchingQuestionResponse(wrongPairs);

      Map<String, String> somewhatWrongPairs = new HashMap<>();
      wrongPairs.put("a", "alpha");
      wrongPairs.put("b", "charlie");
      wrongPairs.put("c", "bravo");

      MatchingQuestionResponse somewhatWrongResponse = new MatchingQuestionResponse(wrongPairs);

      correctResponse.assignPoints(wrongResponse, 3);
      assertEquals(0, wrongResponse.getPointsReceived());
      correctResponse.assignPoints(somewhatWrongResponse, 3);
      assertEquals(1, somewhatWrongResponse.getPointsReceived());
   }
}
