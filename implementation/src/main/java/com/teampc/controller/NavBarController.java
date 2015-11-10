package com.teampc.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * Created by james on 10/26/15.
 */
public class NavBarController {

   @FXML
   void onCreateTestClick(ActionEvent event) throws IOException {
      Stage stage = new Stage();
      Pane createTest = FXMLLoader.load(getClass().getClassLoader().getResource("create-test-options.fxml"));

      Scene scene = new Scene(createTest);
      stage.setScene(scene);
      stage.show();
   }

   @FXML
   void onTakeTestClick(ActionEvent event) throws IOException {
      Stage stage = new Stage();
      Pane takeTest = FXMLLoader.load(getClass().getClassLoader().getResource("take-test-base.fxml"));

      Scene scene = new Scene(takeTest);
      stage.setTitle("Take a Test");
      stage.setScene(scene);
      stage.show();
   }

}
