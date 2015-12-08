package com.teampc.controller;

import com.teampc.controller.test.StudentSubmissionRowController;
import com.teampc.dao.SubmissionDAO;
import com.teampc.model.testtaking.Submission;
import com.teampc.view.RowView;
import javafx.collections.FXCollections;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class StudentViewGradesController extends ListViewController<RowView> {

   private static final String RESOURCE = "studentview-test-row.fxml";

   @Override
   public void initialize(URL location, ResourceBundle resources) {

      data = FXCollections.observableArrayList();

      List<Submission> mySubmissions = SubmissionDAO.getInstance().fetchMySubmissions();
      mySubmissions.forEach(submission -> data.add(new RowView(new StudentSubmissionRowController(submission), RESOURCE)));

      initView();
   }

}
