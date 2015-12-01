package com.teampc.controller.test;

import com.teampc.model.question.*;

import java.net.URL;
import java.util.ResourceBundle;

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

   protected Question question;
   protected TakeTestController parent;

   /**
    * Initializes the Test Section
    */
   @FXML
   public void initialize(URL fxmlFileLocation, ResourceBundle resources) {
   }

   public void setParent(TakeTestController parent) {
      this.parent = parent;
   }

   public void setQuestion(Question question) {
      this.question = question;

      prompt.setText(question.getPrompt());
   }

   public void onLeave() {

   }
}
