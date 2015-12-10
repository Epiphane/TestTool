package com.teampc.model.testtaking;

import com.google.common.collect.Lists;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import testing.runner.SpestRunner;

import static org.junit.Assert.assertTrue;

@RunWith(SpestRunner.class)
public class QuestionResponseSpestTest
{
   @Before
   public void setUp()
   {
      testObj = new MultipleChoiceQuestionResponse(1, Lists.newArrayList("choice 1", "choice 2", "choice 3", "choice 4"));

   }

   private MultipleChoiceQuestionResponse testObj;

   @Test
   public void assignPointsTest_0() throws Exception
   {

      MultipleChoiceQuestionResponse testResponse1 = MultipleChoiceQuestionResponse.studentResponse(0);
      MultipleChoiceQuestionResponse testResponse2 = MultipleChoiceQuestionResponse.studentResponse(1);
      MultipleChoiceQuestionResponse testResponse3 = MultipleChoiceQuestionResponse.studentResponse(2);

      testObj.assignPoints(testResponse1, 1);
      testObj.assignPoints(testResponse2, 1);
      testObj.assignPoints(testResponse3, 1);

      assertTrue(testResponse1.getPointsReceived() >= 0 && testResponse1.getPointsReceived() <= 1);
      assertTrue(testResponse2.getPointsReceived() >= 0 && testResponse2.getPointsReceived() <= 1);
      assertTrue(testResponse3.getPointsReceived() >= 0 && testResponse3.getPointsReceived() <= 1);
   }
}
