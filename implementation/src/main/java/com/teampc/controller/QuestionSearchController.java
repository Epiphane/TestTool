package com.teampc.controller;

import com.google.common.base.Strings;
import com.google.common.collect.ImmutableList;
import com.teampc.dao.TestDAO;
import com.teampc.model.admin.course.Course;
import com.teampc.model.admin.course.Term;
import com.teampc.model.question.Question;
import com.teampc.model.test.Test;
import com.teampc.utils.TestUtils;
import javafx.beans.InvalidationListener;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.control.cell.ComboBoxListCell;
import javafx.stage.Stage;
import org.hibernate.annotations.Check;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.URL;
import java.time.ZoneId;
import java.util.*;

/**
 * A controller that handles events for the question search UI.
 */
public class QuestionSearchController implements Initializable {
   private static final Logger LOG = LoggerFactory.getLogger(QuestionSearchController.class);

   @FXML
   private Tab courseTab;

   @FXML
   private Tab testTab;

   @FXML
   private Tab questionTab;

   @FXML
   private ListView<String> courseListView;

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

   /**
    * Initialize the Question Search UI with appropriate values for the tests,
    * questions, and courses available.
    */
   @Override
   public void initialize(URL fxmlFileLocation, ResourceBundle resources) {
      initCourseList();
   }

   private void initCourseList() {
      Course c1 = new Course("CPE 101", Term.Fall, 1, 1);
      Course c2 = new Course("CPE 102", Term.Fall, 1, 1);
      List<Course> courses = ImmutableList.of(c1, c2);
      //courseListView.setItems(FXCollections.observableList(tests));
      courseListView.setCellFactory(ComboBoxListCell.forListView("CPE 101", "CPE 102"));
   }

   @FXML
   void onFilterCoursesClick() {

   }
}
