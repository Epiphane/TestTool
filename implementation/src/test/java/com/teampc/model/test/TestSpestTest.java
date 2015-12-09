package com.teampc.model.test;

import com.google.common.collect.Lists;
import com.google.common.collect.Range;
import com.rits.cloning.Cloner;
import com.teampc.model.question.Question;
import com.teampc.model.testtaking.MultipleChoiceQuestionResponse;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import testing.CombinationSupport;
import testing.JavaTestUtility;
import testing.runner.SpestRunner;

import java.io.File;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static testing.JavaTestUtility.getFieldValue;

@RunWith(SpestRunner.class)
public class TestSpestTest
{
   @Before
   public void setUp()
   {
      testObj = new com.teampc.model.test.Test("Test", new Date(System.currentTimeMillis() - 1000 * 60 * 60 * 24), new Date(), "Spest");
      testObj.setQuestions(Lists.newArrayList());
      IntStream.range(1, 5).forEach(x -> testObj.getQuestions().add(new Question<>(0, "Question", 2, new MultipleChoiceQuestionResponse(x, Lists.newArrayList("1", "2", "3", "4")), Question.QuestionType.MULTIPLE_CHOICE)));
   }

   /*Start generated tests*/
   private Class clazz = com.teampc.model.test.Test.class;

   private Cloner cloner = new Cloner();
   private File rootDirectory = new File(".");
   private File sourceFile = new File("src/main/java/com/teampc/model/test/Test.java");
   private JavaTestUtility javaTestUtility = new JavaTestUtility(rootDirectory, sourceFile, false);
   private com.teampc.model.test.Test testObj;

   @Test
   public void toStringTest_0() throws Exception
   {

      String methodId = "toString";

      testObj.toString();
      setUp();
   }

   @Test
   public void moveQuestionTest_1() throws Exception
   {
      int testComboIndex;

      String methodId = "moveQuestion_int_int";
      List<java.lang.Integer> testPoints_0 = IntStream.range(1, testObj.getQuestions().size()).mapToObj(x -> x).collect(Collectors.toList());
      List<java.lang.Integer> testPoints_1 = IntStream.range(1, testObj.getQuestions().size()).mapToObj(x -> x).collect(Collectors.toList());
      int[][] combinations = CombinationSupport.getCombinations(testPoints_0.size(), testPoints_1.size());

      int param_0;
      int param_1;
      for(testComboIndex = 0; testComboIndex < combinations.length; testComboIndex++)
      {
         param_0 = testPoints_0.get(combinations[testComboIndex][0]);
         param_1 = testPoints_1.get(combinations[testComboIndex][1]);

         testObj.moveQuestion(param_0, param_1);
         setUp();
      }
   }

   @Test
   public void publishTest_2() throws Exception
   {

      testObj.publish();
      Assert.assertTrue(testObj.isPublished());
      setUp();
   }
    /*End generated tests*/
}
