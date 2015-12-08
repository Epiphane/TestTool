package com.teampc.controller.test;

import com.teampc.controller.ViewSubmissionsController;
import com.teampc.controller.grading.SubmissionResultsController;
import com.teampc.dao.TestDAO;
import com.teampc.model.test.Test;
import com.teampc.model.testtaking.Submission;
import com.teampc.utils.FXUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ToggleButton;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by james on 11/9/15.
 */
public class StudentTestRowController implements Initializable{

   private static final Logger LOG = LoggerFactory.getLogger(StudentTestRowController.class);



   @FXML
   /** Text field showing the status of the test */
   private Text testStatus;

   @FXML
   /** Button to view the questions in a test **/
   private Button viewButton;

   @FXML
   /** Text field showing the name of the test **/
   private Text testName;

//   private Test test;
   private Submission submission;

   public StudentTestRowController(Submission s) {
      this.submission = s;

      // List<Question> theQuestions = new List<Question>();

      // this.test.questions = theQuestions;
   }

   @FXML
   /** Button click handler **/
   void onPublicStatusClickHandler(ActionEvent event) {
//      TestDAO.getInstance().updateTest(new Test());
   }

   @FXML
   /** Button click handler **/
   void onEditActionHandler(ActionEvent event) {
      TestDAO.getInstance().findById(submission.getTest().getId());

      Stage stage = FXUtils.getStageFromEvent(event);
      try {
         FXUtils.switchToScreen(stage, "studentview-questions-list.fxml");
      } catch (IOException e) {
         LOG.error("Failed to load question list view" + e.getMessage());
      }
   }

   @FXML
   /** Button click handler **/
   void onViewActionHandler(ActionEvent event) {
      LOG.debug("Grade Button Clicked");
      try {
         FXUtils.switchToScreenAndConfigureController((Stage) ((Node) event.getSource()).getScene().getWindow(), "submission-results.fxml",
            (controller, stage) -> {
               try {
                  SubmissionResultsController submissionResultsController = (SubmissionResultsController)controller;
                  submissionResultsController.setUp(submission);
               } catch (Exception e) {
                  LOG.error("could not create SubmissionResultsControllers", e);
               }
            });
      }
      catch (IOException e) {
         LOG.error("could not show details for submission " + submission.toString(), e);
      }
   }

   @Override
   /** Initializes the row view **/
   public void initialize(URL location, ResourceBundle resources) {
      testName.setText(submission.getTest().getName());
      testStatus.setText(submission.getTest().isPublished() ? "Completed" : "Incomplete");
   }
}
