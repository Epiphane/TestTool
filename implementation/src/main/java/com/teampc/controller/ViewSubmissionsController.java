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

public class ViewSubmissionsController extends ListViewController<RowView> {

   private static final String RESOURCE = "view-grade.fxml";

   private Test currentTest;

   @Override
   public void initialize(URL location, ResourceBundle resources) {
      data = FXCollections.observableArrayList();
   }

   private Submission s;

   // todo: alright, let's fix this later :(
   public void setCurrentTest(Test test) {
      this.currentTest = test;

      this.s = new Submission();
      this.s.responses.add(new MultipleChoiceQuestionResponse());
      this.s.responses.add(new MultipleChoiceQuestionResponse());
      this.s.taker = UserSession.loggedInUser;

      Collection<Submission> questionList = new ArrayList<Submission>();
      questionList.add(s);
      // SubmissionDAO.getInstance().fetchSubmissionsForTest(currentTest);
      questionList.forEach(submission -> data.add(new RowView(new SubmissionRowController(submission.getTaker()), RESOURCE)));

      initView();
   }


}
