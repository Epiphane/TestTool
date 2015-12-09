package com.teampc.model.testtaking;

import com.google.common.collect.Lists;
import com.sun.org.apache.xpath.internal.operations.Mult;
import com.teampc.model.testtaking.QuestionResponse;
import testing.CombinationSupport;

import org.junit.runner.RunWith;
import testing.runner.SpestRunner;
import org.junit.Before;
import org.junit.Test;
import org.junit.Assert;

import testing.JavaTestUtility;
import format.ClassNameFormat;
import com.teampc.model.testtaking.QuestionResponse;

import java.io.File;
import com.rits.cloning.Cloner;

import java.util.*;

import static org.junit.Assert.assertTrue;
import static testing.JavaTestUtility.getFieldValue;

@RunWith(SpestRunner.class)
public class QuestionResponseTest
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

      assertTrue(testResponse1.getPointsReceived() >= 0);
      assertTrue(testResponse2.getPointsReceived() >= 0);
      assertTrue(testResponse3.getPointsReceived() >= 0);
   }
}
