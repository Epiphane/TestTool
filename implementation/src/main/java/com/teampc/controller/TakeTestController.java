package com.teampc.controller;

import com.teampc.model.test.*;
import com.teampc.model.question.*;
import com.teampc.utils.FXUtils;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.Node;
import javafx.scene.layout.Pane;
import javafx.scene.control.*;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.collections.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.net.URL;
import java.time.ZoneId;
import java.util.*;

public class TakeTestController implements Initializable {
   private static final Logger LOG = LoggerFactory.getLogger(TakeTestController.class);

   @FXML
   private ResourceBundle resources;

   @FXML
   private URL location;

   @FXML
   private Text testTitle;

   @FXML
   private Text descQuestions;

   @FXML
   private Text descTimeLimit;

   @FXML
   private Pane questionPane;

   private Test test;

   /**
    * Initializes the Create Test Options UI with values for the selection lists, spinner, and input boxes
    */
   @FXML
   public void initialize(URL fxmlFileLocation, ResourceBundle resources) {
   }

   public void setTest(Test test) throws IOException {
      this.test = test;

      this.testTitle.setText(test.getCourseName() + " " + test.getName());
      this.descQuestions.setText("There are " + test.getQuestions().size() + " questions.");

      int timeLimit = test.getTimeLimit();
      if (timeLimit > 0) {
         this.descTimeLimit.setText("You have " + test.getTimeLimit() / 60 + " minutes.");
      }
      else {
         this.descTimeLimit.setText("");
      }


      FXMLLoader loader = new FXMLLoader(FXUtils.class.getClassLoader().getResource("question/begin-test.fxml"));
      loader.setController(this);

      Scene newScene = new Scene(loader.load());
      
      questionPane.getChildren().add(newScene.getRoot());
   }

   /**
    * Handler for the create test button
    */
   @FXML
   void onBegin(ActionEvent event) throws IOException {
      LOG.info("Begin!");

      Scene newScene = FXUtils.newSceneAndConfigureController("question/short-answer.fxml", controller -> {});
   
      questionPane.getChildren().add(newScene.getRoot());
   }

   /**
    * Handler for the create test button
    */
   @FXML
   void onNext(ActionEvent event) throws IOException {
   }

   /**
    * Handler for the create test button
    */
   @FXML
   void onPrev(ActionEvent event) throws IOException {
   }
}
