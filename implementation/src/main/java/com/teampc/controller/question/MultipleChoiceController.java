package com.teampc.controller.question;

import com.teampc.controller.TakeTestController;
import com.teampc.model.admin.*;
import com.teampc.model.test.*;
import com.teampc.model.testtaking.*;
import com.teampc.model.question.*;
import com.teampc.utils.FXUtils;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.Node;
import javafx.scene.layout.AnchorPane;
import javafx.scene.control.*;
import javafx.stage.Stage;
import javafx.collections.*;
import javafx.beans.value.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.net.URL;
import java.time.ZoneId;
import java.util.*;

public class MultipleChoiceController extends TestSectionController {
   private static final Logger LOG = LoggerFactory.getLogger(MultipleChoiceController.class);

   @FXML
   private ResourceBundle resources;

   @FXML
   private AnchorPane answers;

   private MultipleChoiceQuestionResponse qResponse;

   private final ToggleGroup answer = new ToggleGroup();

   /**
    * Initializes the Test Section
    */
   @FXML
   public void initialize(URL fxmlFileLocation, ResourceBundle resources) {
   }

   public void setQuestion(QuestionResponse response) {
      super.setQuestion(response);

      qResponse = (MultipleChoiceQuestionResponse) response;

      // Add Radio buttons...      
      ArrayList<String> options = ((MultipleChoiceQuestion) qResponse.getQuestion()).getAnswers();

      for (int i = 0; i < options.size(); i ++) {
         String option = options.get(i);
         RadioButton button = new RadioButton(option);
         button.setToggleGroup(answer);
         if (i == qResponse.getAnswer()) {
            button.setSelected(true);
         }

         button.setLayoutY(i * 30);
         answers.getChildren().add(button);
      }
   }

   public void onLeave() {
      if (qResponse == null) {
         LOG.info("ERROR: No question response set!!");
      }

      String answer = ((RadioButton) this.answer.getSelectedToggle()).getText();

      ArrayList<String> options = ((MultipleChoiceQuestion) qResponse.getQuestion()).getAnswers();
      qResponse.setAnswer(options.indexOf(answer));

      LOG.info("Saving multiple choice response: " + answer);
   }
}
