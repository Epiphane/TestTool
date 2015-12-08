package com.teampc.controller;

import com.teampc.controller.test.StudentTestRowController;
import com.teampc.controller.test.TestRowController;
import com.teampc.dao.SubmissionDAO;
import com.teampc.dao.TestDAO;
import com.teampc.model.test.Test;
import com.teampc.model.testtaking.Submission;
import com.teampc.view.RowView;
import javafx.collections.FXCollections;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class StudentViewTestsController extends ListViewController<RowView> {

   private static final String RESOURCE = "studentview-test-row.fxml";

   @Override
   public void initialize(URL location, ResourceBundle resources) {

      data = FXCollections.observableArrayList();
      
      List<Submission> mySubmissions = SubmissionDAO.getInstance().fetchMySubmissions();
      mySubmissions.forEach(submission -> data.add(new RowView(new StudentTestRowController(submission), RESOURCE)));

      initView();
   }

}
