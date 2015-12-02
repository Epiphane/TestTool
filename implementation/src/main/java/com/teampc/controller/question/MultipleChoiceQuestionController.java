package com.teampc.controller.question;

import com.teampc.model.question.InvalidQuestionException;
import com.teampc.model.question.Question;
import com.teampc.model.testtaking.MultipleChoiceQuestionResponse;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.util.List;

/**
 * Created by adufrene on 11/30/15.
 */
public class MultipleChoiceQuestionController implements QuestionTypeController<MultipleChoiceQuestionResponse> {

   @FXML
   private ListView<String> choices;

   @FXML
   private TextField answerField;

   private ToggleGroup group;

   /**
    * Setup multiple choice question view; perform layout modifications
    */
   @FXML
   private void initialize() {
      group = new ToggleGroup();
      choices.setCellFactory(listview -> new RadioListCell());
   }

   /**
    * Add new row to list of current multiple choice options
    */
   @FXML
   private void newRow() {
      if (answerField.getText().isEmpty()) {
         return;
      }

      int selectedIndex = getSelectedIndex();
      choices.getItems().add(answerField.getCharacters().toString());
      answerField.setText("");

      if (selectedIndex >= 0) {
         group.getToggles().get(selectedIndex).setSelected(true);
      }
   }

   /**
    * {@inheritDoc}
     */
   @Override
   public Question<MultipleChoiceQuestionResponse> createQuestion(String prompt) throws InvalidQuestionException {
      Question<MultipleChoiceQuestionResponse> question = new Question<>();
      question.setPrompt(prompt);
      question.setType(Question.QuestionType.MULTIPLE_CHOICE);
      List<String> stringChoices = choices.getItems();
      int correctAnswer = getSelectedIndex();
      if (correctAnswer == -1) {
         throw new InvalidQuestionException("No correct answer selected for multiple choice question");
      }
      question.setCorrectAnswer(new MultipleChoiceQuestionResponse(correctAnswer, stringChoices));
      return question;
   }

   // C/P'd from http://stackoverflow.com/questions/30027953/javafxlistview-with-radio-button
   private class RadioListCell extends ListCell<String> {
      @Override
      protected void updateItem(String item, boolean empty) {
         super.updateItem(item, empty);

         if (empty) {
            setText(null);
            setGraphic(null);
         } else {
            RadioButton radioButton = new RadioButton(item);
            radioButton.setUserData(item);
            radioButton.setToggleGroup(group);
            // Add Listeners if any
            setGraphic(radioButton);
         }
      }
   }

   /**
    * get selected choice's index
    */
   private int getSelectedIndex() {
      if (group.getSelectedToggle() == null) {
         return -1;
      }
      return choices.getItems().indexOf(group.getSelectedToggle().getUserData());
   }
}
