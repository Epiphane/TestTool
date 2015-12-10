package com.teampc.controller;

import com.teampc.controller.grading.SubmissionResultsController;
import com.teampc.controller.test.TakeTestController;
import com.teampc.dao.TestDAO;
import com.teampc.model.admin.User;
import com.teampc.model.test.Test;
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

import com.teampc.model.testtaking.*;

import java.util.*;

/**
 * This is a child Controller for ViewSubmissionsController. It is the controller for each row that displays
 * information about a submission
 */
@AllArgsConstructor
public class SubmissionRowController implements Initializable {

   /** standard TeamPC logger */
   private static final Logger LOG = LoggerFactory.getLogger(SubmissionRowController.class);

   /** A Text field that shows the name of the student */
   @FXML
   private Text studentName;

   /** A Text field that shows the grade the student earned on this submission */
   @FXML
   private Text studentGrade;

   /** The current submission that we are displaying information for */
   private Submission submission;

   /** The test that this submission is for */
   private Test currentTest;

   /** Initializes this controller with a given Submission and Test */
   public SubmissionRowController(Submission s, Test t) {
      this.submission = s;
      this.currentTest = t;
   }

   @Override
   /** Initializes the row view **/
   public void initialize(URL location, ResourceBundle resources) {
      studentName.setText(submission.taker.getDisplayName());
      studentGrade.setText("" + submission.grade);
   }

   /** Click handler for the "View Details" button. Will show a screen that shows grade for each question */
   @FXML
   void onViewDetailsClickHandler(ActionEvent event) {
      LOG.debug("hello from onViewDetailsClickHandler");
      // todo: show individual questions and allow professor to comment/change grade

      Test currentSelection = currentTest;

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
}
