package com.teampc.controller.test;

import com.google.common.base.Strings;
import com.teampc.dao.TestDAO;
import com.teampc.model.test.Test;
import com.teampc.utils.TestUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.stage.Stage;
import jfxtras.scene.control.LocalDateTimeTextField;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.URL;
import java.time.LocalDateTime;
import java.util.*;

public class CreateTestController implements Initializable {
   private static final Logger LOG = LoggerFactory.getLogger(CreateTestController.class);

   @FXML
   private MenuButton courseType;

   @FXML
   private CheckBox autoGenerateQuestions;

   @FXML
   private LocalDateTimeTextField endDate;

   @FXML
   private MenuButton assignedStudentsList;

   @FXML
   private Spinner numberOfQuestions;

   @FXML
   private LocalDateTimeTextField startDate;

   @FXML
   private TextField testName;

   @FXML
   private CheckBox enableStartDate;

   @FXML
   private CheckBox enableEndDate;

   /**
    * Initializes the Create Test Options UI with values for the selection lists, spinner, and input boxes
    */
   @Override
   public void initialize(URL fxmlFileLocation, ResourceBundle resources) {
      initCourseList();

      initStudentList();

      // init number of questions spinner
      numberOfQuestions.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 100, 0)); //TODO: replace with max number of questions

      startDate.setDisable(true);
      endDate.setDisable(true);
   }

   private void initStudentList() {
      Collection<MenuItem> studentListData = new ArrayList<>();
      Collections.addAll(studentListData, new MenuItem("307-01"), new MenuItem("307-02")); //TODO: replace with real student lists
      studentListData.forEach(menuItem -> {
         menuItem.setOnAction(event -> assignedStudentsList.setText(menuItem.getText()));
      });

      assignedStudentsList.getItems().addAll(studentListData);
   }

   private void initCourseList() {
      Collection<MenuItem> courseData = new ArrayList<>();
      Collections.addAll(courseData, new MenuItem("307 - Software Engineering"), new MenuItem("101 - Intro to Computer Science")); //TODO: replace with real courses
      courseData.forEach(menuItem -> {
         menuItem.setOnAction(event -> courseType.setText(menuItem.getText()));
      });

      courseType.getItems().addAll(courseData);
   }

   /**
    * Enable or Disable a test's start date
    */
   @FXML
   void onStartDateEnable(ActionEvent event) {
      startDate.setDisable(!enableStartDate.isSelected());
   }

   /**
    * Enable or Disable a test's end date
    */
   @FXML
   void onEndDateEnable(ActionEvent event) {
      endDate.setDisable(!enableEndDate.isSelected());
   }

   /**
    * Handler for the create test button
    */
   @FXML
   void onSubmitCreateTest(ActionEvent event) {
      if (Strings.isNullOrEmpty(testName.getText()) || Strings.isNullOrEmpty(courseType.getText())) {
         LOG.info("missing test name, courseType, ...");
         return;
      }

      Date userStartDate = null;
      Date userEndDate = null;

      LocalDateTime startLocalDate = startDate.getDisplayedLocalDateTime();
      LocalDateTime endLocalDate = endDate.getDisplayedLocalDateTime();

      if (startLocalDate != null) { userStartDate = TestUtils.localDateToDate(startLocalDate); }
      if (endLocalDate != null) { userEndDate = TestUtils.localDateToDate(endLocalDate); }

      Test newTest = new Test(testName.getText(), userStartDate, userEndDate, courseType.getText());
      TestDAO.getInstance().insert(newTest);
      LOG.info("new test submitted");

      // TODO: remove this and change to the generate questions view
      Node source = (Node) event.getSource();
      Stage stage = (Stage) source.getScene().getWindow();
      stage.close();
   }

}
