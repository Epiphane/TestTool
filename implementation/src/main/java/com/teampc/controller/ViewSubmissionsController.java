package com.teampc.controller;

import com.teampc.dao.SubmissionDAO;
import com.teampc.model.test.Test;
import com.teampc.model.testtaking.Submission;
import com.teampc.view.RowView;
import javafx.collections.FXCollections;

import java.net.URL;
import java.util.*;
import com.teampc.model.testtaking.*;
import com.teampc.model.admin.access.*;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.text.Text;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * controller for viewing all the submissions for a given test. Will show the test taker, score, and a button to
 * see more information
 */
public class ViewSubmissionsController extends ListViewController<RowView<SubmissionRowController>> {
   /** our teamPC logger */
   private static final Logger LOG = LoggerFactory.getLogger(SubmissionRowController.class);

   /** correspodning view to this file */
   private static final String RESOURCE = "view-grade.fxml";

   /** the test that we are showing the grades for */
   private Test currentTest;

   /** Text field that shows the name of this test */
   @FXML
   private Text testName;

   /** Text fild that shows the class this test belongs to */
   @FXML
   private Text className;

   /** Gets the display string for the currentTest
    * follows the format Test Name - Course Name
    * may leave out a field if one of them is null
    * */
   private String getTestDisplayStr(Test t) {
      if (t == null) {
         return "null Test";
      }

      String str = "";
      if (t.getName() != null) {
         str += t.getName();
      }
      if (t.getCourse() != null) {
         str += " - " + t.getCourse();
      }
      return str;
   }

   /**
    * initialize this view. we exepect all the @FXML members to be initialized at this point
    */
   @Override
   public void initialize(URL location, ResourceBundle resources) {
      data = FXCollections.observableArrayList();
   }

   // canned data
   private Submission s;

   /**
    * Sets the curret test that we are grading. Will update the view accrodingly
    */
   public void setCurrentTest(Test test) {
      this.currentTest = test;

      testName.setText(getTestDisplayStr(currentTest));
      className.setText(currentTest.getCourseName());

      Collection<Submission> submissions = SubmissionDAO.getInstance().fetchSubmissionsForTest(currentTest);
      Key key = currentTest.generateKey();

      if (key == null) {
         LOG.error("could not get key");
      }
      else {
         final Key keyPC = key;
         submissions.stream().forEach(nextSubmission -> nextSubmission.gradeTest(keyPC));

         submissions.forEach(submission -> data.add(new RowView<>(new SubmissionRowController(submission, currentTest), RESOURCE)));
      }
      initView();
   }


}
