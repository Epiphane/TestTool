package com.teampc.controller.question;

import com.google.common.base.Preconditions;
import com.teampc.model.question.InvalidQuestionException;
import com.teampc.model.question.Question;
import com.teampc.model.testtaking.MultipleChoiceQuestionResponse;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.*;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.Optional;

import static com.google.common.base.Preconditions.checkElementIndex;

/**
 * Created by adufrene on 11/30/15.
 *
 */
@Slf4j
public class MultipleChoiceQuestionController implements QuestionTypeController<MultipleChoiceQuestionResponse> {

   @FXML
   private ListView<String> choices;

   @FXML
   private TextField answerField;

   private ToggleGroup group;

   private int correctAnswerIndex = -1;

   /**
    * Setup multiple choice question view; perform layout modifications
    */
   @FXML
   @SuppressWarnings("unused")
   private void initialize() {
      group = new ToggleGroup();
      choices.setCellFactory(listview -> new RadioListCell());
   }

   /**
    * Add new row to list of current multiple choice options
    */
   @FXML
   @SuppressWarnings("unused")
   private void newRow() {
      if (answerField.getText().isEmpty()) {
         return;
      }
      addNewOption(answerField.getText());
   }

   /**
    * Add new option to list of options
     */
   private void addNewOption(String option) {
      group.getToggles().clear();
      choices.getItems().add(option);
      answerField.setText("");
   }

   /**
    * {@inheritDoc}
     */
   @Override
   public Question<MultipleChoiceQuestionResponse> createQuestion(String prompt, Optional<Integer> questionId) throws InvalidQuestionException {
      Question<MultipleChoiceQuestionResponse> question = new Question<>();
      question.setPrompt(prompt);
      question.setType(Question.QuestionType.MULTIPLE_CHOICE);
      List<String> stringChoices = choices.getItems();
      if (correctAnswerIndex == -1) {
         throw new InvalidQuestionException("No correct answer selected for multiple choice question");
      }
      question.setCorrectAnswer(new MultipleChoiceQuestionResponse(correctAnswerIndex, stringChoices));
      questionId.ifPresent(question::setId);
      return question;
   }

   /**
    * {@inheritDoc}
     */
   @Override
   public void setQuestion(MultipleChoiceQuestionResponse questionResponse) {
      questionResponse.getChoices().stream().forEach(this::addNewOption);
      correctAnswerIndex = questionResponse.getAnswer();
   }

   // C/P'd from http://stackoverflow.com/questions/30027953/javafxlistview-with-radio-button
   /**
    * Custom list cell that contains a radio button and remove button
    */
   private class RadioListCell extends ListCell<String> {
      /**
       * {@inheritDoc}
        */
      @Override
      protected void updateItem(String item, boolean empty) {
         super.updateItem(item, empty);

         if (empty) {
            setGraphic(null);
         } else {
            URL layoutURL = getClass().getClassLoader().getResource("multiple-choice-row.fxml");
            if (layoutURL == null) {
               log.error("could not find resource multiple-choice-row.fxml");
               return;
            }
            FXMLLoader fxmlLoader = new FXMLLoader(layoutURL);
            try {
               fxmlLoader.load();
            } catch (IOException e) {
               log.error("Error creating multiple choice row");
               return;
            }

            int thisIndex = choices.getItems().indexOf(item);
            checkElementIndex(thisIndex, choices.getItems().size(), "Somehow adding item that isn't in list of choices");

            MultipleChoiceRowController rowController = fxmlLoader.getController();
            rowController.getText().setText(item);
            rowController.getSelected().setUserData(item);
            rowController.getSelected().setToggleGroup(group);
            rowController.getSelected().selectedProperty()
               .addListener((observable, oldValue, newValue) -> {
                  if (newValue) {
                     correctAnswerIndex = thisIndex;
                  }
               });

            rowController.setParentDelete(MultipleChoiceQuestionController.this::deleteRow);

            if (thisIndex != correctAnswerIndex) {
               rowController.getSelected().setSelected(false);
            } else {
               rowController.getSelected().setSelected(true);
            }

            setGraphic(rowController.getBox());
         }
      }
   }

   @FXML
   void onKeyReleased(KeyEvent event) throws IOException {
      if (event.getCode().equals(KeyCode.ENTER)) {
         ActionEvent.fireEvent(event.getTarget(), new ActionEvent());
      }
   }

   /**
    * Delete the row containing the specified item, and update the currently selected item as necessary
     */
   private void deleteRow(String item) {
      // Update selected...
      int toDeleteIndex = choices.getItems().indexOf(item);
      checkElementIndex(toDeleteIndex, choices.getItems().size(), "Trying to delete item not in choices");

      if (toDeleteIndex < correctAnswerIndex) {
         // move correct answer index up one
         --correctAnswerIndex;
      } else if (toDeleteIndex == correctAnswerIndex) {
         // invalidate correct answer index
         correctAnswerIndex = -1;
      }
      // else: we don't need to update index

      // Delete at index as double check for our index, and because we've already got the index
      choices.getItems().remove(toDeleteIndex);
   }
}
