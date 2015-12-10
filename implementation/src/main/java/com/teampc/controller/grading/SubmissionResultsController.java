package com.teampc.controller.grading;

import com.teampc.controller.questionview.QuestionViewController;
import com.teampc.dao.SubmissionDAO;
import com.teampc.model.admin.Student;
import com.teampc.model.admin.Teacher;
import com.teampc.model.admin.User;
import com.teampc.model.admin.access.UserSession;
import com.teampc.model.question.Question;
import com.teampc.model.testtaking.QuestionResponse;
import com.teampc.model.testtaking.Submission;
import com.teampc.utils.FXUtils;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by zarend on 12/8/15.
 *
 * Controller for showing results of a submission
 */
public class SubmissionResultsController implements Initializable {
   private static final Logger LOG = LoggerFactory.getLogger(SubmissionResultsController.class);

   /** Controller for current question */
   private QuestionViewController currentQuestionController;

   /** Submission to be viewed */
   private Submission submission;

   /** Flag for if we are grading */
   private boolean isGrading;

   /** index of current question */
   private int currQuestionIdx = 0; // TODO: prevent this from going less than 0 or more than numQuestions - 1

   /** reference to question pane */
   @FXML
   private ScrollPane questionPane;

   /** next button on screen */
   @FXML
   private Button nextBtn;

   /** previous button on screen */
   @FXML
   Button prevBtn;

   /** Text field showing points received */
   @FXML
   TextField pointsReceived;

   /** Comment of grading */
   @FXML
   TextField gradingComment;

   /** Button for saving grading */
   @FXML
   Button saveGrading;

   /** Grading pang */
   @FXML
   private Pane gradingPane;

   /** Set up layout, primarily setting appropriate fields and flags */
   public void setUp(Submission s) {
      submission = s;

      boolean isInstructor = UserSession.getLoggedInUser().isTeacher();

      isGrading = isInstructor;

      gradingPane.setDisable(!isGrading);
      drawCurrQuestion();
   }

   /** Draws the current question on screen */
   private void drawCurrQuestion() {
      Question question = getCurrQuestion();
      QuestionResponse questionResponse = getCurrQuestionResponse();

      if (question == null) {
         LOG.debug("question is null");
         return;
      }

      String fileString = question.getType().getFileString();
      LOG.info("Loading " + fileString + "...");
      FXMLLoader loader = new FXMLLoader(FXUtils.class.getClassLoader().getResource("question/" + fileString + ".fxml"));

      try {
         Scene newScene = new Scene(loader.load());
         questionPane.setContent(newScene.getRoot());

         currentQuestionController = loader.getController();
         currentQuestionController.setQuestion(question);
         currentQuestionController.setResponse(questionResponse);
         currentQuestionController.freeze();
         drawButtonStuff();

         pointsReceived.setText("" + questionResponse.getPointsReceived());
      }
      catch (IOException e) {
         LOG.error("could not load currQuestion", e);
      }
   }

   /** Draw the previous and next buttons */
   private void drawButtonStuff() {
      prevBtn.setDisable(currQuestionIdx == 0);
      nextBtn.setDisable(currQuestionIdx >= getNumQuestions() - 1);
   }

   /** Return the current question being displayed */
   private Question getCurrQuestion() {
      return submission.getTest().getQuestions().get(currQuestionIdx);
   }

   /** Return the current question response */
   private QuestionResponse getCurrQuestionResponse() {
      return submission.getResponses().get(currQuestionIdx);
   }

   /** Return the number of questions */
   private int getNumQuestions() {
      return Math.min(submission.getTest().getQuestions().size(), submission.getResponses().size());
   }

   /** Initialize layout */
   @Override
   public void initialize(URL location, ResourceBundle resources) {

   }

   /** Callback for clicking next button */
   @FXML
   void onClickNextBtn() {
      LOG.debug("clicked Next");
      if (currQuestionIdx < getNumQuestions() - 1) {
         currQuestionIdx++;
         drawCurrQuestion();
      }
   }

   /** Callback for clicking previous button */
   @FXML
   void onClickPrevBtn() {
      LOG.debug("clicked Prev");
      if (currQuestionIdx > 0) {
         currQuestionIdx--;
         drawCurrQuestion();
      }
   }

   /** Callback for clicking save button */
   @FXML
   void onClickSaveBtn() {
      LOG.debug("clicked Save");
      QuestionResponse questionResponse = getCurrQuestionResponse();
      questionResponse.setPointsReceived(Integer.parseInt(pointsReceived.getText()));
      questionResponse.setComment(gradingComment.getText());

      LOG.info("saving submission to DB...");
      SubmissionDAO.getInstance().update(submission);
   }
}
