package com.teampc.controller.grading;

import com.teampc.controller.questionview.QuestionViewController;
import com.teampc.dao.SubmissionDAO;
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
 */
public class SubmissionResultsController implements Initializable {
   private static final Logger LOG = LoggerFactory.getLogger(SubmissionResultsController.class);

   private QuestionViewController currentQuestionController;

   private Submission submission;

   private boolean isGrading;

   private int currQuestionIdx = 0; // TODO: prevent this from going less than 0 or more than numQuestions - 1

   @FXML
   private ScrollPane questionPane;

   @FXML
   private Button nextBtn;

   @FXML
   Button prevBtn;

   @FXML
   TextField pointsReceived;

   @FXML
   TextField gradingComment;

   @FXML
   Button saveGrading;

   public void setUp(Submission s) {
      submission = s;

      boolean isInstructor = true; // FIXME: determine if the current user is an instructor
      isGrading = isInstructor;

      drawCurrQuestion();
   }

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

         pointsReceived.setText("" + questionResponse.getPointsReceived());
      }
      catch (IOException e) {
         LOG.error("could not load currQuestion", e);
      }
   }

   private Question getCurrQuestion() {
      return submission.getTest().getQuestions().get(currQuestionIdx);
   }

   private QuestionResponse getCurrQuestionResponse() {
      return submission.getResponses().get(currQuestionIdx);
   }

   @Override
   public void initialize(URL location, ResourceBundle resources) {
   }

   @FXML
   void onClickNextBtn() {
      LOG.debug("clicked Next");
      currQuestionIdx++;
      drawCurrQuestion();
   }

   @FXML
   void onClickPrevBtn() {
      LOG.debug("clicked Prev");
      currQuestionIdx--;
      drawCurrQuestion();
   }

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