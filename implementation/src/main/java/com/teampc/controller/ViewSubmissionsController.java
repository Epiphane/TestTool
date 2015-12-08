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

public class ViewSubmissionsController extends ListViewController<RowView<SubmissionRowController>> {
   private static final Logger LOG = LoggerFactory.getLogger(SubmissionRowController.class);

   private static final String RESOURCE = "view-grade.fxml";

   private Test currentTest;

   @FXML
   private Text testName;

   @FXML
   private Text className;

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

   @Override
   public void initialize(URL location, ResourceBundle resources) {
      data = FXCollections.observableArrayList();
   }

   // canned data
   private Submission s;

   // todo: alright, let's fix this later :(
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
