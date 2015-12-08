package com.teampc.controller.question;


import com.teampc.dao.QuestionDAO;
import com.teampc.model.admin.course.Course;
import com.teampc.model.admin.course.Term;
import com.teampc.model.question.Question;
import com.teampc.model.test.Test;
import com.google.common.collect.ImmutableList;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.util.Callback;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.URL;
import java.util.*;
import java.util.stream.Collectors;

/**
 * A controller that handles events for the question search UI.
 */
public class QuestionSearchController implements Initializable {
   private static final Logger LOG = LoggerFactory.getLogger(QuestionSearchController.class);

   private Course exampleCourse1;
   private Course exampleCourse2;

   private Test t1;
   private Test t2;

   private Question exampleQ1;
   private Question exampleQ2;
   private Question exampleQ3;

   private Course currentCourse;
   private Test currentTest;

   private List<Test> allTests;

   private Stage primaryStage;

   /** The UI element containing the organization tabs*/
   @FXML
   private TabPane tabPane;

   /** The UI tab of courses */
   @FXML
   private Tab courseTab;

   /** The UI tab of tests */
   @FXML
   private Tab testTab;

   @FXML
   private Tab questionTab;

   @FXML
   private ListView<Course> courseListView;

   @FXML
   private ListView<Test> testListView;

   @FXML
   private ListView<Question> questionListView;

   @FXML
   private TextArea filterCourseInput;

   @FXML
   private TextArea filterTestInput;

   @FXML
   private TextArea filterQuestionInput;

   @FXML
   private Button filterCourseButton;

   @FXML
   private Button filterTestButton;

   @Override
   /**
    * Initialize the Question Search UI with appropriate values for the tests,
    * questions, and courses available, and set up handlers for click events.
    *
      pre: courseListView == null && testListView == null && questionListView == null
    *
      post: courseListView != null && testListView != null && questionListView != null
    */
   public void initialize(URL fxmlFileLocation, ResourceBundle resources) {
      //set up the change handler for tab pane
      tabPane.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Tab>() {
         @Override
         public void changed(ObservableValue<? extends Tab> obs, Tab oldVal, Tab newVal) {
            if (oldVal == courseTab && newVal == testTab) {
               LOG.info("Changed tab from courses to tests");
               LOG.info("Filtering tests by selected course: " + currentCourse);
               setTestListByCourse(currentCourse);
            }
         }
      });

      questionListView.setCellFactory(param -> new QuestionRow());

      //Set up the question list stuff
      questionListView.setOnMouseClicked(new EventHandler<MouseEvent>() {
         @Override
         public void handle(MouseEvent event) {
            LOG.info("Selected question: " + questionListView.getSelectionModel().getSelectedItems());
         }
      });
      initQuestionList();

      //Set the course list view click handler
      courseListView.setOnMouseClicked(new EventHandler<MouseEvent>() {
         @Override
         public void handle(MouseEvent event) {
            LOG.info("Selected course: " + courseListView.getSelectionModel().getSelectedItem());
            currentCourse = courseListView.getSelectionModel().getSelectedItem();
         }
      });
      initCourseList();
      //Set up the test list stuff
      testListView.setOnMouseClicked(new EventHandler<MouseEvent>() {
         @Override
         public void handle(MouseEvent event) {
            LOG.info("Selected test: " + testListView.getSelectionModel().getSelectedItem());
            currentTest = testListView.getSelectionModel().getSelectedItem();
         }
      });
      initTestList();

   }

   /**
    * Initialize the course list
    */
   private void initCourseList() {
      exampleCourse1 = new Course("CPE 101", Term.Fall, 2015, 1);
      exampleCourse2 = new Course("CPE 102", Term.Fall, 2015, 1);
      ObservableList courses = FXCollections.observableArrayList(exampleCourse1, exampleCourse2);
      courseListView.setItems(courses);
   }

   private void initTestList() {
      //TODO fill this with real data instead of dummies
      Date dummyStartDate = new Date(System.currentTimeMillis() - 24 * 1000 * 60 * 60);
      Date dummyEndDate=  new Date(System.currentTimeMillis() + 24 * 1000 * 60 * 60);
      //CPE 101 Midterm
      t1 = new Test("Midterm 1", dummyStartDate, dummyEndDate, exampleCourse1.getTitle());
      t1.setQuestions(new ArrayList<>(ImmutableList.of(exampleQ1, exampleQ2)));
      t1.setCourse(exampleCourse1);
      //CPE 102 Midterm
      t2 = new Test("Midterm 1", dummyStartDate, dummyEndDate, exampleCourse2.getTitle());
      t2.setQuestions(new ArrayList<>(ImmutableList.of(exampleQ3)));
      t2.setCourse(exampleCourse2);

      allTests = ImmutableList.of(t1, t2);
   }

   private void initQuestionList() {
      exampleQ1 = new Question();
      exampleQ2 = new Question();
      exampleQ3 = new Question();

      exampleQ1.setPrompt("A ________ converts a source code program into machine code.");
      exampleQ1.setPoints(5);

      exampleQ2.setPrompt("Program variables are stored in which part of the computer?");
      exampleQ2.setPoints(5);

      exampleQ3.setPrompt("Functions on Java classes are called _______");
      exampleQ3.setPoints(3);

      questionListView.getItems().addAll(QuestionDAO.getInstance().fetchAll());
   }

   private void setTestListByCourse(Course currentCourse) {
      if (currentCourse != null) {
         testListView.setItems(FXCollections.observableList(allTests.stream().filter(
            (test) -> test.getCourse().getTitle().equals(currentCourse.getTitle())).collect(Collectors.toCollection(ArrayList::new))));
      } else {
         testListView.setItems(FXCollections.observableList(allTests));
      }
   }

   @FXML
   /**
    * Filter the course list when the filter course list button is pressed.
    pre: courseListView != null
    post:
    */
   public void onFilterCoursesClick() {
      LOG.info("filterCourseInput text: " + filterCourseInput.getParagraphs().get(0));
      courseListView.setItems(FXCollections.observableList(
         courseListView.getItems().stream().filter((course -> course.getTitle().contains(filterCourseInput.getParagraphs().get(0)))).collect(Collectors.toCollection(ArrayList::new))));
   }

   @FXML
   /**
    * Filter the test list when the filter course list button is pressed.
    pre: testListView != null
    post:
    */
   public void onFilterTestsClick() {
      LOG.info("filterTestInput text: " + filterTestInput.getParagraphs().get(0));
      testListView.setItems(FXCollections.observableList(
         testListView.getItems().stream().filter((test -> test.toString().contains(filterTestInput.getParagraphs().get(0)))).collect(Collectors.toCollection(ArrayList::new))));
   }

   public void setPrimaryStage(Stage stage) {
      primaryStage = stage;
   }

   private static class QuestionRow extends ListCell<Question> {
      @Override
      protected void updateItem(Question item, boolean empty) {
         super.updateItem(item, empty);
         if (empty) {
            setText(null);
         } else {
            setText(String.format(Locale.getDefault(), "%s -- %s -- %s", item.getPrompt(), item.getType(), item.getCorrectAnswer()));
         }
      }
   }
}
