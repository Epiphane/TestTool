package com.teampc.controller;

import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Lists;
import com.teampc.controller.question.MatchingQuestionController;
import com.teampc.controller.question.QuestionTableController;
import com.teampc.dao.QuestionDAO;
import com.teampc.dao.SubmissionDAO;
import com.teampc.dao.TestDAO;
import com.teampc.model.admin.Student;
import com.teampc.model.admin.Teacher;
import com.teampc.model.admin.access.UserSession;
import com.teampc.model.admin.course.Course;
import com.teampc.model.question.Question;
import com.teampc.model.test.Test;
import com.teampc.model.test.TestDifficulty;
import com.teampc.model.testtaking.Key;
import com.teampc.model.testtaking.MatchingQuestionResponse;
import com.teampc.model.testtaking.MultipleChoiceQuestionResponse;
import com.teampc.model.testtaking.Submission;
import com.teampc.utils.FXUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.stage.Stage;
import lombok.Setter;

import java.io.IOException;
import java.util.Collections;
import java.util.Date;
import java.util.List;

/**
 * NavBarController handles the main navigation bar for the Instructors.
 * @author Jameson li
 */
public class NavBarController {

   @Setter
   private Stage primaryStage;

   @FXML
   private Button fakeDataButton;

   @FXML
   private void initialize() {
      if (UserSession.getLoggedInUser() instanceof Student) {
         fakeDataButton.setVisible(false);
      } else {
         fakeDataButton.setVisible(true);
      }
   }

   @FXML
   void onCreateTestClick(ActionEvent event) {
      try {
         showLayout("create-test-options.fxml");
      } catch (IOException e) {
         e.printStackTrace();
      }

   }

   @FXML
   void onTakeTestClick(ActionEvent event) {
      try {
         showLayout("take-test-base.fxml");
      } catch (IOException e) {
         e.printStackTrace();
      }
   }

   @FXML
   private void openQuestionBank() throws IOException {
      QuestionTableController.showQuestionTable(primaryStage);
   }

   @FXML
   void onViewTestsClick(ActionEvent event) {
      try {
         showLayout("view-tests-list.fxml");
      } catch (IOException e) {
         e.printStackTrace();
      }

   }

   private void showLayout(String resource) throws IOException {
      FXUtils.newScreenAndConfigureController(resource, FXUtils::noop);
   }

   @FXML
   private void addFakeData() {
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

      new Alert(Alert.AlertType.INFORMATION, "Inserted fake data", ButtonType.CLOSE).show();
   }
}
