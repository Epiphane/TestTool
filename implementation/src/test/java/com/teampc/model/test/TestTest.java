package com.teampc.model.test;

import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Lists;
import com.teampc.dao.QuestionDAO;
import com.teampc.model.automation.TestGrader;
import com.teampc.model.question.Question;
import com.teampc.model.testtaking.MatchingQuestionResponse;
import com.teampc.model.testtaking.MultipleChoiceQuestionResponse;
import com.teampc.model.testtaking.ShortAnswerQuestionResponse;
import org.junit.Assert;
import org.junit.Test;

import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import static org.junit.Assert.assertEquals;

/**
 * Created by zarend on 12/9/15.
 */
public class TestTest {

   @Test
   public void testIsOpen() {
      // TODO
   }

   @Test
   public void testTakeTest() {
      // TODO
   }

   @Test
   public void testRemoveQuestion() {
      // TODO
   }

   @Test
   public void testMoveQuestionUp() {
      // TODO
   }

   @Test
   public void testMoveQuestionDown() {
      com.teampc.model.test.Test test = makeFakeTest();
      int originalSize = test.getQuestions().size();

      Question<MultipleChoiceQuestionResponse> question1 = test.getQuestions().get(1);

      int originalIdx = test.getQuestions().indexOf(question1);
      test.moveQuestionDown(question1);
      Assert.assertTrue(test.getQuestions().get(2).equals(question1));
      Assert.assertTrue(test.getQuestions().size() == originalSize);
   }

   @Test
   public void testGenerateKey() {
      com.teampc.model.test.Test test = makeFakeTest();

      test.generateKey();
      TestGrader.gradeTest(test.getKey(), test.getKey());
      Assert.assertTrue(test.getKey().grade == 5.0);
   }

   private com.teampc.model.test.Test makeFakeTest() {
      Question<MultipleChoiceQuestionResponse> question1 = new Question<>();
      question1.setPoints(1);
      question1.setPrompt("What is the name of the version control tool used in CPE 307?");
      question1.setType(Question.QuestionType.MULTIPLE_CHOICE);

      MultipleChoiceQuestionResponse mcCorrectAnswer = new MultipleChoiceQuestionResponse(1, Lists.newArrayList("git", "svn", "mercurial", "repo"));
      question1.setCorrectAnswer(mcCorrectAnswer);

      Question<MatchingQuestionResponse> question2 = new Question<>();
      question2.setPoints(3);
      question2.setPrompt("Match the model classes to their corresponding test classes.");
      question2.setType(Question.QuestionType.MATCHING);

      MatchingQuestionResponse matchingCorrectAnswer = new MatchingQuestionResponse(ImmutableMap.of(
         "Question.java", "QuestionTest.java",
         "Test.java", "TestTest.java",
         "User.java", "UserTest.java"
      ));

      question2.setCorrectAnswer(matchingCorrectAnswer);

      Question<ShortAnswerQuestionResponse> question3 = new Question<>();
      question3.setPoints(5);
      question3.setPrompt("which IDE do we use?");
      question3.setType(Question.QuestionType.SHORT_ANSWER);

      ShortAnswerQuestionResponse shortAnswerQuestionResponse = new ShortAnswerQuestionResponse("IntelliJ", ShortAnswerQuestionResponse.MatchType.EXACTLY);

      question3.setCorrectAnswer(shortAnswerQuestionResponse);

      List<Question> questionList = Lists.newArrayList(question1, question2, question3);

      QuestionDAO.getInstance().insert(questionList);

      Date userStartDate = new GregorianCalendar().getTime();
      GregorianCalendar temp = new GregorianCalendar();
      temp.add(GregorianCalendar.HOUR, 24);
      Date userEndDate = temp.getTime();

      com.teampc.model.test.Test fakeTest = new com.teampc.model.test.Test("Midterm 1", userStartDate, userEndDate, "CPE 307");
      fakeTest.setQuestions(questionList);

      return fakeTest;
   }
}
