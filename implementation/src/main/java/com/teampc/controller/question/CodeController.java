package com.teampc.controller.question;

import com.teampc.controller.test.TestSectionController;
import com.teampc.model.testtaking.CodeQuestionResponse;
import com.teampc.model.testtaking.QuestionResponse;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.URL;
import java.util.ResourceBundle;

public class CodeController extends TestSectionController {
   private static final Logger LOG = LoggerFactory.getLogger(CodeController.class);

   @FXML
   private ResourceBundle resources;

   @FXML
   private TextArea response;

   private CodeQuestionResponse qResponse;

   /**
    * Initializes the Test Section
    */
   @FXML
   public void initialize(URL fxmlFileLocation, ResourceBundle resources) {
   }

   public void setQuestion(QuestionResponse response) {
      super.setQuestion(response);

      qResponse = (CodeQuestionResponse) response;

      this.response.setText(qResponse.getCodeAnswer());
   }

   public void onLeave() {
      if (qResponse == null) {
         LOG.info("ERROR: No question response set!!");
      }

      qResponse.setCodeAnswer(response.getText());

      LOG.info("Saving short answer response: " + response.getText());
   }
}
