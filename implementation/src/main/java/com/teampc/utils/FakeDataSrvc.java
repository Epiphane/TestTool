package com.teampc.utils;

import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Lists;
import com.teampc.dao.QuestionDAO;
import com.teampc.dao.SubmissionDAO;
import com.teampc.dao.TestDAO;
import com.teampc.model.admin.Teacher;
import com.teampc.model.admin.access.UserSession;
import com.teampc.model.question.Question;
import com.teampc.model.test.Test;
import com.teampc.model.testtaking.Key;
import com.teampc.model.testtaking.MatchingQuestionResponse;
import com.teampc.model.testtaking.MultipleChoiceQuestionResponse;
import com.teampc.model.testtaking.Submission;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;

import java.util.Date;
import java.util.List;

import static java.util.stream.Collectors.toList;

/**
 * Created by zarend on 12/8/15.
 */
public class FakeDataSrvc {
   public static void addFakeData() {
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

      List<Question> questionList = Lists.newArrayList(question1, question2);

      QuestionDAO.getInstance().insert(questionList);

      Test fakeTest = new Test("Midterm 1", new Date(0), new Date(0), "CPE 307");
      fakeTest.setQuestions(questionList);

      Teacher fakeTeacher = new Teacher("c00l te@cher", "Gene", "Fisher", "1 luv te@ching!");
      fakeTest.setOwner(fakeTeacher);

      Key fakeKey = new Key();
      fakeKey.setComplete(true);
      fakeKey.setResponses(fakeTest.getQuestions().stream().map(Question::getCorrectAnswer).collect(toList()));
      fakeKey.setTest(fakeTest);

      fakeTest.setKey(fakeKey);

      TestDAO.getInstance().insert(fakeTest);

      Submission fakeSubmission = new Submission();
      fakeSubmission.setComplete(true);
      fakeSubmission.setTaker(UserSession.getLoggedInUser());
      fakeSubmission.setTest(fakeTest);
      fakeSubmission.setGraded(false);
      fakeSubmission.setResponses(Lists.newArrayList(
         MultipleChoiceQuestionResponse.studentResponse(0),
         new MatchingQuestionResponse(ImmutableMap.of(
            "Question.java", "QuestionTest.java",
            "Test.java", "UserTest.java",
            "User.java", "TestTest.java"
         ))
      ));
      fakeSubmission.setGrade(0.0f);

      SubmissionDAO.getInstance().insert(fakeSubmission);
   }
}
