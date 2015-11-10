package com.teampc.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
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
   private void openQuestionBank() throws IOException {
      QuestionTableController.showQuestionTable(primaryStage);
   }

   void onViewTestsClick(ActionEvent event) {
      try {
         showLayout("view-test-list.fxml");
      } catch (IOException e) {
         e.printStackTrace();
      }

   }

   private void showLayout(String resource) throws IOException {
      Stage stage = new Stage();
      Parent parent = FXMLLoader.load(getClass().getClassLoader().getResource(resource));

      Scene scene = new Scene(parent);
      stage.setScene(scene);
      stage.show();
   }
}
