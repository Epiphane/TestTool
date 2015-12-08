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

public class ViewSubmissionsController extends ListViewController<RowView<SubmissionRowController>> {

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
      Key key = currentTest.getKey();

      // CANNED DATA
      if (submissions.isEmpty()) {
         Submission s = new Submission();
         s.responses.add(new MultipleChoiceQuestionResponse());
         s.responses.add(new MultipleChoiceQuestionResponse());
         s.responses.add(new ShortAnswerQuestionResponse("", ShortAnswerQuestionResponse.MatchType.ALL));
         s.responses.add(new ShortAnswerQuestionResponse("", ShortAnswerQuestionResponse.MatchType.ALL));
         s.taker = UserSession.getLoggedInUser();

         submissions = new ArrayList<>();
         submissions.add(s);

         key = new Key();
         key.responses.add(new MultipleChoiceQuestionResponse());
         key.responses.add(new MultipleChoiceQuestionResponse(1, new ArrayList<>()));
         key.responses.add(new ShortAnswerQuestionResponse("", ShortAnswerQuestionResponse.MatchType.ALL));
         key.responses.add(new ShortAnswerQuestionResponse("one", ShortAnswerQuestionResponse.MatchType.ALL));
      }

      final Key keyPC = key;
      submissions.stream().forEach(nextSubmission -> nextSubmission.gradeTest(keyPC));

      submissions.forEach(submission -> data.add(new RowView<>(new SubmissionRowController(submission), RESOURCE)));
      initView();
   }


}
