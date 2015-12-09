package com.teampc.controller.test;

import com.google.common.base.Strings;
import com.teampc.dao.QuestionDAO;
import com.teampc.dao.TestDAO;
import com.teampc.model.question.Question;
import com.teampc.model.test.Test;
import com.teampc.utils.FXUtils;
import com.teampc.utils.TestFXUtils;
import com.teampc.utils.TestUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import jfxtras.scene.control.LocalDateTimeTextField;
import lombok.Setter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.URL;
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

/**
 * The test creation controller provides methods that allow the model and views to interact.
 * View changes when creating a test will be persistent in the model through this class.
 * Changes to a test on the model will be displayed in the view through the use of
 * methods in this class
 *
 * @author Jameson Li (jrli@calpoly.edu)
 */
public class CreateTestController implements Initializable {
   private static final Logger LOG = LoggerFactory.getLogger(CreateTestController.class);

   @FXML
   /** The type of Course this test will be assigned to */
   private MenuButton courseType;

   @FXML
   /** The checkbox to show if questions will be automatically generated */
   private CheckBox autoGenerateQuestions;

   @FXML
   /** The end date of the test **/
   private LocalDateTimeTextField endDate;

   @FXML
   /** The list of students that could potentially be assigned to this test */
   private MenuButton assignedStudentsList;

   @FXML
   /** The number of questions in this test **/
   private Spinner<Integer> numberOfQuestions;

   @FXML
   /** The start date of this test **/
   private LocalDateTimeTextField startDate;

   @FXML
   /** The name of this test **/
   private TextField testName;

   @FXML
   /** Checkbox that enables the start date field **/
   private CheckBox enableStartDate;

   @FXML
   /** Checkbox that enables the end date field **/
   private CheckBox enableEndDate;

   @Setter
   private List<Question> questions = new ArrayList<>();

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

   /**
    * Retrieves list of students and adds it to the view
    */
   private void initStudentList() {
      Collection<MenuItem> studentListData = new ArrayList<>();
      Collections.addAll(studentListData, new MenuItem("307-01"), new MenuItem("307-02")); //TODO: replace with real student lists
      studentListData.forEach(menuItem -> {
         menuItem.setOnAction(event -> assignedStudentsList.setText(menuItem.getText()));
      });

      assignedStudentsList.getItems().addAll(studentListData);
   }

   /**
    * Retrieves list of courses and adds it to the view
    */
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
    pre:
      testName.getText() != null && !testName.getText().equals("") &&
      courseType.getText() != null && !courseType.getText().equals("")
    *
    post:
      forall(Question question; questions'.contains(question);
               generateQuestions().contains(question));
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
      questions.addAll(generateQuestions());
      newTest.setQuestions(questions);
      TestDAO.getInstance().insert(newTest);
      LOG.info("new test submitted");

      TestFXUtils.openTestViewer(FXUtils.getStageFromEvent(event), newTest, TestEvent.VIEW_EVENT);
   }

   /**
    * Generate the number of requested questions
    pre: numberOfQuestions.getValue() != null & numberOfQuestions.getValue() >= 0
    *
    post:
      return'.size() == numberOfQuestions.getValue() &&
      forall(Question question; return'.contains(question);
         QuestionDAO.getInstance().fetchAll().contains(question))
    */
   private Collection<Question> generateQuestions() {
      return QuestionDAO.getInstance().fetchAll().stream().limit(numberOfQuestions.getValue()).collect(Collectors.toList());
   }

}
