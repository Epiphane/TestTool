package com.teampc.controller;

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

/**
 * Created by james on 11/9/15.
 */
public class TestRowController implements Initializable{

   private static final Logger LOG = LoggerFactory.getLogger(TestRowController.class);

   @FXML
   private Button gradeButton;

   @FXML
   private Button publishButton;

   @FXML
   private Button editButton;

   @FXML
   private Text testStatus;

   @FXML
   private Button viewButton;

   @FXML
   private ToggleButton publicStatus;

   @FXML
   private Text testName;

   private Test test;

   public TestRowController(Test test) {
      this.test = test;
   }

   @FXML
   void onPublicStatusClickHandler(ActionEvent event) {
      TestDAO.getInstance().updateTest(new Test());
   }

   @FXML
   void onEditActionHandler(ActionEvent event) {

   }

   @FXML
   void onPublishActionHandler(ActionEvent event) {
      TestDAO.getInstance().updateTest(new Test());
   }

   @FXML
   void onGradeActionHandler(ActionEvent event) {

   }

   @FXML
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
   public void initialize(URL location, ResourceBundle resources) {
      testName.setText(test.getName());
      testStatus.setText(test.isPublished() ? "Completed" : "Incomplete");
      publicStatus.setSelected(test.isPublished());
   }
}
