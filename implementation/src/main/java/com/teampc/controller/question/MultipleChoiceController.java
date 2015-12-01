package com.teampc.controller.question;


import com.teampc.controller.test.TestSectionController;
import com.teampc.model.testtaking.MultipleChoiceQuestionResponse;
import com.teampc.model.testtaking.QuestionResponse;
import javafx.fxml.FXML;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.AnchorPane;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

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
      List<String> options = qResponse.getChoices();

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

      List<String> options = qResponse.getChoices();
      qResponse.setAnswer(options.indexOf(answer));

      LOG.info("Saving multiple choice response: " + answer);
   }
}
