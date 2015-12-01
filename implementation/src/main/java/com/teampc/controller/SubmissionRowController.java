package com.teampc.controller;

import com.teampc.dao.TestDAO;
import com.teampc.model.admin.User;
import com.teampc.model.test.Test;
import com.teampc.utils.FXUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ToggleButton;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import com.teampc.model.testtaking.*;

/**
 * Created by james on 11/9/15.
 */
public class SubmissionRowController implements Initializable{

   private static final Logger LOG = LoggerFactory.getLogger(SubmissionRowController.class);

   @FXML
   private Text studentName;

   private User user;

   private Submission s;
   private Key key;

   public SubmissionRowController(User user) {
      this.user = user;

      this.s = new Submission();
      this.s.responses.add(new MultipleChoiceQuestionResponse());
      this.s.responses.add(new MultipleChoiceQuestionResponse());
      this.s.responses.add(new ShortAnswerQuestionResponse());
      this.s.responses.add(new ShortAnswerQuestionResponse());

      this.key = new Key();
      this.key.responses.add(new MultipleChoiceQuestionResponse());
      this.key.responses.add(new MultipleChoiceQuestionResponse(1));
      this.key.responses.add(new ShortAnswerQuestionResponse());
      this.key.responses.add(new ShortAnswerQuestionResponse("one"));
   }

   @Override
   /** Initializes the row view **/
   public void initialize(URL location, ResourceBundle resources) {
      s.gradeTest(key);

      studentName.setText(user.getDisplayName() + s.grade);
   }
}
