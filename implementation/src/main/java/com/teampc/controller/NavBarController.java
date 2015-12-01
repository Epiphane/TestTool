package com.teampc.controller;

import com.teampc.controller.question.QuestionTableController;
import com.teampc.utils.FXUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.stage.Stage;
import lombok.Setter;

import java.io.IOException;

/**
 * Created by james on 10/26/15.
 */
public class NavBarController {

   @Setter
   private Stage primaryStage;

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
      FXUtils.newScreenAndConfigureController(resource, (x, y) -> {});
   }

}
