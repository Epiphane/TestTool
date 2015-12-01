package com.teampc.controller;

import com.teampc.dao.TestDAO;
import com.teampc.model.test.Test;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ToggleButton;
import javafx.scene.text.Text;

import java.net.URL;
import java.util.ResourceBundle;

import com.teampc.utils.FXUtils;

import java.io.*;
import java.util.*;

/**
 * Created by james on 11/9/15.
 */
public class TestRowController implements Initializable{

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

      // List<Question> theQuestions = new List<Question>();

      // this.test.questions = theQuestions;
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
      // try {
         // FXUtils.switchToScreenAndConfigureController("view-grade.fxml",(stage, NewController controller) -> {
         //    controller.setSubmission(this.test);
         // });
      // } catch (IOException e) {
      //    e.printStackTrace();
      // }
   }

   @FXML
   void onViewActionHandler(ActionEvent event) {
      TestDAO.getInstance().findById("id-01");
   }

   @Override
   public void initialize(URL location, ResourceBundle resources) {
      testName.setText(test.getName());
      testStatus.setText(test.isPublished() ? "Completed" : "Incomplete");
      publicStatus.setSelected(test.isPublished());
   }
}
