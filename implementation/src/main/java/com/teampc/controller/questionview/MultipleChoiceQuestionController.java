package com.teampc.controller.questionview;

import com.teampc.model.question.InvalidQuestionException;
import com.teampc.model.question.Question;
import com.teampc.model.testtaking.QuestionResponse;
import com.teampc.model.testtaking.MultipleChoiceQuestionResponse;
import javafx.fxml.FXML;
import javafx.scene.layout.Pane;
import javafx.scene.control.*;

import java.util.List;

/**
 * Created by adufrene on 11/30/15.
 */
public class MultipleChoiceQuestionController extends QuestionViewController<MultipleChoiceQuestionResponse> {

   @FXML
   private Pane choices;

   private List<String> options;

   private ToggleGroup group;

   /**
    * Setup multiple choice question view; perform layout modifications
    */
   @FXML
   private void initialize() {
      group = new ToggleGroup();
      // choices.setCellFactory(listview -> new RadioListCell());
   }

   /**
    * get selected choice's index
    */
   private int getSelectedIndex() {
      if (group.getSelectedToggle() == null) {
         return -1;
      }
      return options.indexOf(((RadioButton) group.getSelectedToggle()).getText());
   }

   /**
    * Set the question for editing or answering
    */
   @Override
   public void setQuestion(Question question) {
      super.setQuestion(question);

      options = ((MultipleChoiceQuestionResponse) question.getCorrectAnswer()).getChoices();
      for (int i = 0; i < options.size(); i ++) {
         String option = options.get(i);
         RadioButton button = new RadioButton(option);
         button.setToggleGroup(group);

         button.setLayoutY(i * 30);
         choices.getChildren().add(button);
      }
   }

   /**
    * Auto populate an existing response
    */
   public void setResponse(MultipleChoiceQuestionResponse response) {
      group.getToggles().get(response.getAnswer()).setSelected(true);
   }

   /**
    * Get the selected MC response
    */
   public MultipleChoiceQuestionResponse getResponse() {
      if (getSelectedIndex() == -1) return null;

      return new MultipleChoiceQuestionResponse(getSelectedIndex(), options);
   }

   public void freeze() {
      choices.setDisable(true);
   }
}
