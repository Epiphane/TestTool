package com.teampc.controller.test;

import com.teampc.model.admin.access.UserSession;
import com.teampc.controller.questionview.QuestionViewController;
import com.teampc.model.test.*;
import com.teampc.model.testtaking.*;
import com.teampc.model.question.*;
import com.teampc.utils.FXUtils;
import com.teampc.dao.SubmissionDAO;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.FXML;
import javafx.stage.Stage;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.ScrollPane;
import javafx.scene.text.Text;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

/**
 * TakeTestController handles the test taking session, displays questions, and
 * persists the student's submission to the Database.
 *
 * @author tsteinke
 */
public class TakeTestController {
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
   private ScrollPane questionPane;

   @FXML
   private Text questionNumber;

   /* Question stuff */
   @FXML
   private Text prompt;

   private Test test;
   private QuestionViewController currentQuestionController;
   private int currentQuestion;

   private Submission submission;

   private SubmissionDAO submissionDAO;

   public TakeTestController() {
      submissionDAO = SubmissionDAO.getInstance();
   }

   /**
    * Sets the test being taken
    */
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

   /**
    * Loads a question and draws the UI for it
    */
   private void drawQuestionUI(String fileString, Question question) throws IOException {
      LOG.info("Loading " + fileString + "...");
      FXMLLoader loader = new FXMLLoader(FXUtils.class.getClassLoader().getResource("question/" + fileString + ".fxml"));

      if (question == null) {
         questionNumber.setText("");

         currentQuestionController = null;
         loader.setController(this);
      }

      Scene newScene = new Scene(loader.load());
      if (question != null) {
         questionNumber.setText("Question " + (currentQuestion + 1));

         currentQuestionController = loader.getController();
         currentQuestionController.setQuestion(question);
      }

      LOG.info("Adding question to pane...");
      questionPane.setContent(newScene.getRoot());
   }

   /**
    * Sets which question is currently being worked on
    */
   private void setQuestion(int qNumber) throws IOException {
      if (submission == null) {
         drawQuestionUI("begin-test", null);
         return;
      }

      currentQuestion = qNumber;
      questionNumber.setText("");

      int numQuestions = submission.getTest().getQuestions().size();
      if (currentQuestion < 0) {
         currentQuestion = 0;
      }
      
      if (currentQuestion >= numQuestions) {
         currentQuestion = numQuestions;
         drawQuestionUI("complete-test", null);
         return;
      }

      Question question = submission.getTest().getQuestions().get(currentQuestion);

      drawQuestionUI(question.getType().getFileString(), question);

      if (currentQuestionController != null) {
         // Attempt to auto-populate response
         QuestionResponse response = submission.getResponses().get(currentQuestion);

         if (response != null) {
            currentQuestionController.setResponse(response);
         }
      }
   }

   @FXML
   public void onBegin(ActionEvent event) throws IOException {
      if (submission != null) {
         throw new IOException("Cannot begin a test. There is already a submission");
      }

      submission = test.takeTest(UserSession.loggedInUser);
      submissionDAO.insert(submission);

      setQuestion(0);
   }

   private void save() {
      if (currentQuestionController != null) {
         QuestionResponse response = currentQuestionController.getResponse();

         if (response == null) {
            return;
         }
         
         response.setQuestion(test.getQuestions().get(currentQuestion));
         submission.getResponses().set(currentQuestion, response);
      }
   }

   @FXML
   public void onNext(ActionEvent event) throws IOException {
      save();

      setQuestion(currentQuestion + 1);
   }

   @FXML
   public void onPrev(ActionEvent event) throws IOException {
      save();

      setQuestion(currentQuestion - 1);
   }

   @FXML
   public void onSubmitTest(ActionEvent event) {
      LOG.info("Submit Test responses");

      Node source = (Node) event.getSource();
      Stage stage = (Stage) source.getScene().getWindow();

      stage.close();
   }
}
