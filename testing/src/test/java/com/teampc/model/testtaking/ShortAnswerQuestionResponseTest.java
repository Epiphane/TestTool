package com.teampc.model.testtaking;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Created by adufrene on 12/9/15.
 *
 */
public class ShortAnswerQuestionResponseTest {

   private ShortAnswerQuestionResponse correctAnswer;

   @Before
   public void setup() {
      correctAnswer = new ShortAnswerQuestionResponse("cpe 307, cpe 101, java", ShortAnswerQuestionResponse.MatchType.ANY);
   }

   @Test
   public void testAssignPoints() {
      ShortAnswerQuestionResponse studentResponse = ShortAnswerQuestionResponse.studentResponse("cpe 307 has cpe 101 as a prerequisite and uses java");

      // Test is correct for all
      correctAnswer.setMatchType(ShortAnswerQuestionResponse.MatchType.ALL);
      correctAnswer.assignPoints(studentResponse, 3);
      assertEquals(3, studentResponse.getPointsReceived());

      // Test is not correct for percentage of all
      studentResponse.setAnswer("cpe 307 has cpe 101 as a prerequisite");
      correctAnswer.assignPoints(studentResponse, 3);
      assertEquals(2, studentResponse.getPointsReceived());


      // Test is correct for any
      correctAnswer.setMatchType(ShortAnswerQuestionResponse.MatchType.ANY);
      studentResponse.setAnswer("something cpe 307 something");
      correctAnswer.assignPoints(studentResponse, 3);
      assertEquals(3, studentResponse.getPointsReceived());

      // Test is not correct for any
      studentResponse.setAnswer("Go is the best programming language ever");
      correctAnswer.assignPoints(studentResponse, 3);
      assertEquals(0, studentResponse.getPointsReceived());

      // Test is correct for exact
      correctAnswer.setMatchType(ShortAnswerQuestionResponse.MatchType.EXACTLY);
      studentResponse.setAnswer("cpe 307, cpe 101, java");
      correctAnswer.assignPoints(studentResponse, 3);
      assertEquals(3, studentResponse.getPointsReceived());

      // Test is not correct for exact
      studentResponse.setAnswer("cpe 307, cpe 101, jav");
      correctAnswer.assignPoints(studentResponse, 3);
      assertEquals(0, studentResponse.getPointsReceived());
   }
}
