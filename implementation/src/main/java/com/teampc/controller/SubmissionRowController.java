package com.teampc.controller;

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

@AllArgsConstructor
public class SubmissionRowController implements Initializable{

   private static final Logger LOG = LoggerFactory.getLogger(SubmissionRowController.class);

   @FXML
   private Text studentName;

   @FXML
   private Text studentGrade;

   private Submission submission;

   private Test currentTest;

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

   @FXML
   void onViewDetailsClickHandler(ActionEvent event) {
      LOG.debug("hello from onViewDetailsClickHandler");
      // todo: show individual questions and allow professor to comment/change grade

      Test currentSelection = currentTest;

      try {
         FXUtils.switchToScreenAndConfigureController((Stage) ((Node) event.getSource()).getScene().getWindow(), "take-test.fxml",
            (controller, stage) -> {
               try {
                  TakeTestController takeTestController = (TakeTestController)controller;
                  takeTestController.setTest(currentSelection);
                  takeTestController.setSubmission(submission);
                  takeTestController.setIsGrading(true);

               } catch (Exception e) {
                  LOG.info("ERROR");
                  LOG.info(e.getMessage());
               }
            });
      }
      catch (IOException e) {
         LOG.error("could not show details for submission " + submission.toString(), e);
      }
   }
}
