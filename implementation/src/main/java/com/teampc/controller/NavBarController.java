package com.teampc.controller;

import com.teampc.controller.question.QuestionTableController;
import com.teampc.model.admin.access.UserSession;
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

/**
 * NavBarController handles the main navigation bar for the Instructors.
 * @author Jameson li
 */
public class NavBarController implements HasStage {

   @Setter
   /** Nav bar stage to display UI **/
   private Stage primaryStage;

   @FXML
   /** Button to add fake data **/
   private Button fakeDataButton;

   @FXML
   /**
    * Initializes the nav bar view.
    */
   private void initialize() {
      if (!UserSession.getLoggedInUser().isAdmin()) {
         fakeDataButton.setVisible(false);
      } else {
         fakeDataButton.setVisible(true);
      }
   }

   @FXML
   /** Handler for the create test button **/
   void onCreateTestClick(ActionEvent event) {
      try {
         showLayout("create-test-options.fxml");
      } catch (IOException e) {
         e.printStackTrace();
      }

   }

   @FXML
   /** Handler for the take test button **/
   void onTakeTestClick(ActionEvent event) {
      try {
         showLayout("take-test-base.fxml");
      } catch (IOException e) {
         e.printStackTrace();
      }
   }

   @FXML
   /** Handler for the question bank button **/
   private void openQuestionBank() throws IOException {
      QuestionTableController.showQuestionTable(primaryStage);
   }

   @FXML
   /** Handler for the view tests button **/
   void onViewTestsClick(ActionEvent event) {
      try {
         showLayout("view-tests-list.fxml");
      } catch (IOException e) {
         e.printStackTrace();
      }

   }

   /** Common method used to show the layout given a resource file name **/
   private void showLayout(String resource) throws IOException {
      FXUtils.newScreenAndConfigureController(resource, FXUtils::noop);
   }

   @FXML
   /** Handler to add fake data to application storage **/
   private void addFakeData() {
      FakeDataSrvc.addFakeData();
      new Alert(Alert.AlertType.INFORMATION, "Inserted fake data", ButtonType.CLOSE).show();
   }

   @FXML
   /** Handler to show the courses screen. */
   private void onCoursesClick() {
      try {
         showLayout("course-list.fxml");
      } catch (IOException e) {
         e.printStackTrace();
      }
   }
}
