package com.teampc.controller.test;

import com.teampc.model.question.*;

import java.net.URL;
import java.util.ResourceBundle;

import com.teampc.model.testtaking.QuestionResponse;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.text.Text;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public abstract class TestSectionController implements Initializable {
   private static final Logger LOG = LoggerFactory.getLogger(TestSectionController.class);

   @FXML
   private ResourceBundle resources;

   @FXML
   private Text prompt;

   protected QuestionResponse question;
   protected TakeTestController parent;

   /**
    * Initializes the Test Section
    */
   @FXML
   public void initialize(URL fxmlFileLocation, ResourceBundle resources) {
   }

   public void setParent(TakeTestController parent) {
      LOG.info("Setting parent...");
      this.parent = parent;
   }

   public void setQuestion(QuestionResponse response) {
      LOG.info("Setting question response...");
      this.question = response;

      if (prompt != null) {
         prompt.setText(question.getQuestion().getPrompt());
      }
   }

   public void onLeave() {

   }
}
