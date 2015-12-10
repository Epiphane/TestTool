package com.teampc.model.test;

import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Lists;
import com.teampc.dao.QuestionDAO;
import com.teampc.model.automation.TestGrader;
import com.teampc.model.admin.User;
import com.teampc.model.testtaking.Submission;
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
import static org.junit.Assert.assertTrue;

/**
 * Created by zarend on 12/9/15.
 */
public class TestTest {

   @Test
   public void testIsOpen() {
      Date beforeToday1 = new GregorianCalendar(2013, 10, 11).getTime();     
      Date beforeToday2 = new GregorianCalendar(2014, 10, 11).getTime();     
      Date afterToday1 = new GregorianCalendar(2016, 12, 12).getTime();        
      Date afterToday2 = new GregorianCalendar(2017, 12, 12).getTime();        
      com.teampc.model.test.Test notPublishedTest = new com.teampc.model.test.Test("Not Published", beforeToday2, afterToday1, "Test Course");

      assertTrue(!notPublishedTest.isOpen());

      com.teampc.model.test.Test notOpenTest = new com.teampc.model.test.Test("Not yet open", afterToday1, afterToday2, "Test Course");
      notOpenTest.publish();
      assertTrue(!notOpenTest.isOpen());

      notOpenTest = new com.teampc.model.test.Test("Already closed", beforeToday1, beforeToday2, "Test Course");
      notOpenTest.publish();
      assertTrue(!notOpenTest.isOpen());

      com.teampc.model.test.Test openTest = new com.teampc.model.test.Test("Open test!", beforeToday2, afterToday1, "Test Course");
      openTest.publish();
      assertTrue(openTest.isOpen());
   }

   @Test
   public void testTakeTest() {
      com.teampc.model.test.Test test = makeFakeTest();

      String serializedUser = "STUDENT user first last pass";
      User testStudent = User.fromString(serializedUser);

      Submission testSubmission = test.takeTest(testStudent);

      assertTrue(testSubmission.getTaker().equals(testStudent));      
      assertTrue(testSubmission.getTest().equals(test));      
      assertTrue(testSubmission.getTest().equals(test));      
      assertTrue(!testSubmission.isComplete());      
      assertTrue(testSubmission.getResponses().size() == test.getQuestions().size());    

      // Initially null for all responses
      for (int i = 0; i < test.getQuestions().size(); i ++) {
         assertTrue(testSubmission.getResponses().get(i) == null);
      }  
   }

   @Test
   public void testRemoveQuestion() {
      com.teampc.model.test.Test test = makeFakeTest();
      int originalSize = test.getQuestions().size();

      Question<MultipleChoiceQuestionResponse> question1 = test.getQuestions().get(1);

      test.removeQuestion(question1);
      assertTrue(!test.getQuestions().get(1).equals(question1));
      assertTrue(test.getQuestions().size() == originalSize - 1);
      assertTrue(test.getQuestions().indexOf(question1) <= 0);

      List<Question> questions = test.getQuestions();
      test.removeQuestion(null);
      assertTrue(test.getQuestions().equals(questions));

      test.removeQuestion(new Question());
      assertTrue(test.getQuestions().equals(questions));
   }

   @Test
   public void testMoveQuestionUp() {
      com.teampc.model.test.Test test = makeFakeTest();
      int originalSize = test.getQuestions().size();

      Question<MultipleChoiceQuestionResponse> question1 = test.getQuestions().get(1);

      int originalIdx = test.getQuestions().indexOf(question1);
      test.moveQuestionUp(question1);
      Assert.assertTrue(test.getQuestions().get(0).equals(question1));
      Assert.assertTrue(test.getQuestions().size() == originalSize);

      List<Question> questions = test.getQuestions();
      test.moveQuestionUp(null);
      Assert.assertTrue(test.getQuestions().equals(questions));

      test.moveQuestionUp(new Question());
      Assert.assertTrue(test.getQuestions().equals(questions));

      test.moveQuestionUp(test.getQuestions().get(0));
      Assert.assertTrue(test.getQuestions().equals(questions));
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

      List<Question> questions = test.getQuestions();
      test.moveQuestionDown(null);
      Assert.assertTrue(test.getQuestions().equals(questions));

      test.moveQuestionDown(test.getQuestions().get(test.getQuestions().size() - 1));
      Assert.assertTrue(test.getQuestions().equals(questions));
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
