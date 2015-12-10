package com.teampc.model.testtaking;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertEquals;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.Lists;
import com.teampc.model.question.Question;
import org.junit.Before;
import org.junit.Test;
import org.junit.Assert;

import java.util.HashMap;
import java.util.Map;

/**
 * Tests for the Submission class
 *
 * @author david ellison daelliso@calpoly.edu
 */
public class SubmissionTest {
   private Submission submission;
   MatchingQuestionResponse wrongMatchingResponse;
   MatchingQuestionResponse correctMatchingResponse;
   MultipleChoiceQuestionResponse wrongMCResponse;
   MultipleChoiceQuestionResponse correctMCResponse;

   MultipleChoiceQuestionResponse resp;
   MatchingQuestionResponse maResponse;

   @Before
   public void initialize() {
      Question<MultipleChoiceQuestionResponse> mcQuestion = new Question<>();
      mcQuestion.setPoints(1);
      mcQuestion.setPrompt("Prompt");
      mcQuestion.setType(Question.QuestionType.MULTIPLE_CHOICE);

      resp = new MultipleChoiceQuestionResponse();
      resp.setChoices(ImmutableList.of("Wrong Choice", "Correct Choice", "Another Wrong Choice"));
      resp.setAnswer(1);

      mcQuestion.setCorrectAnswer(resp);
      resp.setQuestion(mcQuestion);

      Question<MatchingQuestionResponse> matchingQuestion = new Question<>();
      matchingQuestion.setPoints(1);
      matchingQuestion.setPrompt("Match the things");
      matchingQuestion.setType(Question.QuestionType.MATCHING);

      Map<String, String> pairs = new HashMap<>();
      pairs.put("Key1", "Value1");
      pairs.put("Key2", "Value2");
      maResponse = new MatchingQuestionResponse(pairs);
      maResponse.setQuestion(matchingQuestion);

      matchingQuestion.setCorrectAnswer(maResponse);

      correctMatchingResponse = new MatchingQuestionResponse(pairs);
      correctMatchingResponse.setQuestion(matchingQuestion);

      pairs = new HashMap<>();
      pairs.put("Key2", "Value1");
      pairs.put("Key1", "Value2");
      wrongMatchingResponse = new MatchingQuestionResponse(pairs);
      wrongMatchingResponse.setQuestion(matchingQuestion);

      correctMCResponse = new MultipleChoiceQuestionResponse();
      correctMCResponse.setChoices(ImmutableList.of("Wrong Choice", "Correct Choice", "Another Wrong Choice"));
      correctMCResponse.setAnswer(1);
      correctMCResponse.setQuestion(mcQuestion);

      wrongMCResponse = new MultipleChoiceQuestionResponse();
      wrongMCResponse.setChoices(ImmutableList.of("Wrong Choice", "Correct Choice", "Another Wrong Choice"));
      wrongMCResponse.setAnswer(2);
      wrongMCResponse.setQuestion(mcQuestion);
   }

   @Test
   public void testGradeTest() {
      Key key = new Key();
      key.setResponses(Lists.newArrayList(resp, maResponse));

      submission = new Submission();
      submission.setResponses(Lists.newArrayList(correctMCResponse, correctMatchingResponse));
      submission.gradeTest(key);
      assertEquals(3.0f, submission.getGrade(), 0.001);

      submission = new Submission();
      submission.setResponses(Lists.newArrayList(correctMCResponse, wrongMatchingResponse));
      submission.gradeTest(key);
      assertEquals(1.0f, submission.getGrade(), 0.001);

      submission = new Submission();
      submission.setResponses(Lists.newArrayList(wrongMCResponse, correctMatchingResponse));
      submission.gradeTest(key);
      assertEquals(2.0f, submission.getGrade(), 0.001);

      submission = new Submission();
      submission.setResponses(Lists.newArrayList(wrongMCResponse, wrongMatchingResponse));
      assertEquals(0.0f, submission.getGrade(), 0.001);
   }
}
