package com.teampc.controller.test;

import java.net.URL;
import java.util.ResourceBundle;

import com.teampc.model.testtaking.QuestionResponse;
import com.teampc.model.testtaking.ShortAnswerQuestionResponse;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ShortAnswerController extends TestSectionController {
   private static final Logger LOG = LoggerFactory.getLogger(ShortAnswerController.class);

   @FXML
   private ResourceBundle resources;

   @FXML
   private TextArea response;

   private ShortAnswerQuestionResponse qResponse;

   /**
    * Initializes the Test Section
    */
   @FXML
   public void initialize(URL fxmlFileLocation, ResourceBundle resources) {
   }

   public void setQuestion(QuestionResponse response) {
      super.setQuestion(response);

      qResponse = (ShortAnswerQuestionResponse) response;

      this.response.setText(qResponse.getAnswer());
   }

   public void onLeave() {
      if (qResponse == null) {
         LOG.info("ERROR: No question response set!!");
      }

      qResponse.setAnswer(response.getText());

      LOG.info("Saving short answer response: " + response.getText());
   }
}
