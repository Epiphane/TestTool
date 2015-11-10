package com.teampc.controller;

import com.teampc.dao.TestDAO;
import com.teampc.model.test.Test;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ToggleButton;
import javafx.scene.text.Text;

/**
 * Created by james on 11/9/15.
 */
public class TestRowController {

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

   public TestRowController(String name, boolean isCompleted, boolean isPublic) {

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
      TestDAO.getInstance().findById("id-01");
   }
}
