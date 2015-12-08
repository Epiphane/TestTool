package com.teampc.controller.test;

import com.teampc.model.admin.access.UserSession;
import com.teampc.controller.questionview.QuestionViewController;
import com.teampc.model.test.*;
import com.teampc.model.testtaking.*;
import com.teampc.model.question.*;
import com.teampc.utils.FXUtils;
import com.teampc.dao.SubmissionDAO;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.ScrollPane;
import javafx.scene.text.Text;
import lombok.Getter;
import lombok.Setter;
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

   /** Edit Stuff **/
   @FXML
   private Pane editQuestionPane;
   @FXML
   private Button editQuestionDelete;
   @FXML
   private Button editQuestionUp;
   @FXML
   private Button editQuestionDown;

   // grading stuff
   @FXML
   private Pane gradingSection;
   @FXML
   private TextField gradingComment;
   @FXML
   private Button saveCommentButton;
   @FXML
   private TextField gradingGradeInput;
   @FXML
   private Button saveGradeButton;

   @Getter
   private boolean isGrading = false;

   private Test test;
   private QuestionViewController currentQuestionController;
   private int currentQuestion;
   private List<Question> questionsList;

   private Submission submission;

   private SubmissionDAO submissionDAO;

   public TakeTestController() {
      submissionDAO = SubmissionDAO.getInstance();
   }

   private void setGradingViewStuff() {
      QuestionResponse response = submission.getResponses().get(currentQuestion);

      gradingComment.setText(response.getComment());
      gradingGradeInput.setText("" + response.getPointsReceived());
   }

   @FXML
   void onClickSaveCommentButton() {
      LOG.debug("clicked \"Save Comment\"");
      if (isGrading) {
         QuestionResponse response = submission.getResponses().get(currentQuestion);
         response.setComment(gradingComment.getText());
         SubmissionDAO.getInstance().update(submission);
      }
   }

   @FXML
   void onClickSaveGradeButton() {
      LOG.debug("clicked \"Save Grade\"");
      if (isGrading) {
         try {
            QuestionResponse response = submission.getResponses().get(currentQuestion);
            response.setPointsReceived(Integer.parseInt(gradingGradeInput.getText()));
            SubmissionDAO.getInstance().update(submission);
         }
         catch (NumberFormatException e) {
            LOG.debug("gradingGradeInput not formatted as Integer", e);
         }
      }
   }

   public void setSubmission(Submission s) {
      submission = s;

   }

   /**
    * Sets the test being taken
    */
   public void setTest(Test test) throws IOException {
      this.test = test;

      this.testTitle.setText(test.getCourseName() + " " + test.getName());
      this.descQuestions.setText("There are " + test.getQuestions().size() + " questions.");
      questionsList = test.getQuestions();

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

      setIsGrading(false);
   }

   public void setIsGrading(boolean isGrading) {
      this.isGrading = isGrading;
      gradingSection.setVisible(this.isGrading);
      if (isGrading) {
         setGradingViewStuff();
         currentQuestion = 0;
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

         if (isGrading) {

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

   @FXML
   public void onDeleteQuestion(ActionEvent event) {
      submission.getTest().removeQuestion(questionsList.get(currentQuestion));
   }

   @FXML
   public void onUpQuestion(ActionEvent event) {
      submission.getTest().moveQuestionUp(questionsList.get(currentQuestion));
   }

   @FXML
   public void onDownQuestion(ActionEvent event) {
      submission.getTest().moveQuestionDown(questionsList.get(currentQuestion));
   }

   public void setEventType(TestEvent eventType) {
      // todo: update ui
      switch (eventType) {
         case EDIT_EVENT:
            editQuestionPane.setVisible(true);
            break;
      }
   }
}
