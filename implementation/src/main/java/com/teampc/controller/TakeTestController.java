package com.teampc.controller;

import com.teampc.model.admin.*;
import com.teampc.model.test.*;
import com.teampc.model.testtaking.*;
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

   @FXML
   private Text questionNumber;

   /* Question stuff */
   @FXML 
   private Text prompt;

   private Test test;
   private int currentQuestion;

   private Submission submission;

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

      if (submission == null) {
         this.setQuestion(-1);
      }
      else {
         this.setQuestion(submission.getNextUnansweredQuestion());
      }
   }

   private void setQuestion(int qNumber) throws IOException {
      int numQuestions = test.getQuestions().size();
      Question question = null;
      if (qNumber < 0) {
         qNumber = -1;

         if (submission != null) {
            qNumber = 0;
         }
      }
      else if (qNumber >= numQuestions) {
         qNumber = numQuestions;
      }
      else {
         question = test.getQuestions().get(qNumber);
      }

      currentQuestion = qNumber;
      String questionFileString = "begin-test";
      questionNumber.setText("");
      if (qNumber >= 0 && qNumber < numQuestions) {
         questionFileString = "short-answer";
         questionNumber.setText("Question " + (qNumber + 1));
      }
      else if (qNumber == numQuestions) {
         questionFileString = "complete-test";
      }

      FXMLLoader loader = new FXMLLoader(FXUtils.class.getClassLoader().getResource("question/" + questionFileString + ".fxml"));
      loader.setController(this);

      Scene newScene = new Scene(loader.load());
      
      questionPane.getChildren().clear();
      questionPane.getChildren().add(newScene.getRoot());

      if (question != null) {
         LOG.info("There's a prompt! Question: " + qNumber);

         prompt.setText(question.getPrompt());
      }
   }

   @FXML
   void onBegin(ActionEvent event) throws IOException {
      if (submission != null) {
         throw new IOException("Cannot begin a test. There is already a submission");
      }

      submission = test.takeTest(new User());

      setQuestion(0);
   }

   @FXML
   void onNext(ActionEvent event) throws IOException {
      setQuestion(currentQuestion + 1);
   }

   @FXML
   void onPrev(ActionEvent event) throws IOException {
      setQuestion(currentQuestion - 1);
   }

   @FXML
   void onSubmitTest(ActionEvent event) throws IOException {
      LOG.info("Submit Test responses");
   }
}
