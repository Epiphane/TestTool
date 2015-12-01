package com.teampc.controller.test;

import com.sun.glass.ui.View;
import com.teampc.dao.TestDAO;
import com.teampc.model.test.Test;
import com.teampc.utils.FXUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ToggleButton;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import com.teampc.utils.FXUtils;

import java.io.*;

/**
 * Created by james on 11/9/15.
 */
public class TestRowController implements Initializable{

   private static final Logger LOG = LoggerFactory.getLogger(TestRowController.class);

   @FXML
   /** Grade Test Button **/
   private Button gradeButton;

   @FXML
   /** Button to publish test **/
   private Button publishButton;

   @FXML
   /** Button to edit test **/
   private Button editButton;

   @FXML
   /** Text field showing the status of the test */
   private Text testStatus;

   @FXML
   /** Button to view the questions in a test **/
   private Button viewButton;

   @FXML
   /** Button to make the test publicly available to test takers **/
   private ToggleButton publicStatus;

   @FXML
   /** Text field showing the name of the test **/
   private Text testName;

   private Test test;

   public TestRowController(Test test) {
      this.test = test;
   }

   @FXML
   /** Button click handler **/
   void onPublicStatusClickHandler(ActionEvent event) {
      TestDAO.getInstance().updateTest(new Test());
   }

   @FXML
   /** Button click handler **/
   void onEditActionHandler(ActionEvent event) {
      LOG.debug("Edit Button Clicked");
   }

   @FXML
   /** Button click handler **/
   void onPublishActionHandler(ActionEvent event) {
      TestDAO.getInstance().updateTest(new Test());
   }

   @FXML
   /** Button click handler **/
   void onGradeActionHandler(ActionEvent event) {
      LOG.debug("Grade Button Clicked");
       try {
          Stage stage = FXUtils.getStageFromEvent(event);

          FXUtils.switchToScreenAndConfigureController(stage, "view-submissions-list.fxml", (controller, gradeStage) -> {
             ViewSubmissionsController control = (ViewSubmissionsController) controller;
             control.setCurrentTest(test);
          });

       } catch (IOException e) {
          e.printStackTrace();
       }
   }

   @FXML
   /** Button click handler **/
   void onViewActionHandler(ActionEvent event) {
      TestDAO.getInstance().findById(test.getId());

      Stage stage = FXUtils.getStageFromEvent(event);
      try {
         FXUtils.switchToScreen(stage, "view-questions-list.fxml");
      } catch (IOException e) {
         LOG.error("Failed to load question list view" + e.getMessage());
      }

   }

   @Override
   /** Initializes the row view **/
   public void initialize(URL location, ResourceBundle resources) {
      testName.setText(test.getName());
      testStatus.setText(test.isPublished() ? "Completed" : "Incomplete");
      publicStatus.setSelected(test.isPublished());
   }
}
