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
import com.teampc.model.admin.User;
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
import com.teampc.utils.FakeDataSrvc;
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

import static java.util.stream.Collectors.toList;

/**
 * NavBarController handles the main navigation bar for the Instructors.
 * @author Jameson li
 */
public class NavBarController implements HasStage {

   @Setter
   private Stage primaryStage;

   @FXML
   private Button fakeDataButton;

   @FXML
   private void initialize() {
      if (!UserSession.getLoggedInUser().isAdmin()) {
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
      FakeDataSrvc.addFakeData();
      new Alert(Alert.AlertType.INFORMATION, "Inserted fake data", ButtonType.CLOSE).show();
   }
}
