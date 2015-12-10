package com.teampc.controller.question;

import com.teampc.model.question.InvalidQuestionException;
import com.teampc.model.question.Question;
import com.teampc.model.testtaking.MatchingQuestionResponse;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import javafx.util.Callback;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.IOException;
import java.util.Optional;

import static java.util.stream.Collectors.toMap;

/**
 * Created by adufrene on 11/30/15.
 *
 * Controller for editing a matching question
 */
public class MatchingQuestionController implements QuestionTypeController<MatchingQuestionResponse> {

   /** Table of matching options */
   @FXML
   private TableView<MatchingPair> options;

   /** Text field for new key */
   @FXML
   private TextField keyField;

   /** Text field for new value */
   @FXML
   private TextField valueField;

    /**
     * Setup screen, make table editable
     */
   @FXML
   @SuppressWarnings("unused")
   private void initialize() {
      options.setEditable(true);
      options.getColumns().get(options.getColumns().size() - 1)
         .setCellFactory(callback -> new RemoveCell<>(options));
   }

   /**
    * Adds a new option to table of matching pairs
    */
   @FXML
   @SuppressWarnings("unused")
   private void newOption() {
      if (keyField.getCharacters().toString().isEmpty() || valueField.getCharacters().toString().isEmpty()) {
         return;
      }
      options.getItems().add(new MatchingPair(keyField.getCharacters().toString(), valueField.getCharacters().toString()));
      keyField.setText("");
      valueField.setText("");
   }

   /**
    * {@inheritDoc}
     */
   @Override
   public Question<MatchingQuestionResponse> createQuestion(String prompt, Optional<Integer> questionId) throws InvalidQuestionException {
      if (options.getItems().isEmpty()) {
         throw new InvalidQuestionException("No pairs created for matching");
      }

      Question<MatchingQuestionResponse> question = new Question<>();
      question.setPrompt(prompt);
      question.setType(Question.QuestionType.MATCHING);
      question.setCorrectAnswer(new MatchingQuestionResponse(
         options.getItems().stream().collect(toMap(MatchingPair::getKey, MatchingPair::getValue))
      ));
      questionId.ifPresent(question::setId);
      return question;
   }

   /**
    * {@inheritDoc}
     */
   @Override
   public void setQuestion(MatchingQuestionResponse questionResponse) {
      ObservableList<MatchingPair> matches = FXCollections.observableArrayList();
      questionResponse.getPairings()
         .forEach((key, value) -> matches.add(new MatchingPair(key, value)));
      options.setItems(matches);
   }

   /** Callback for when enter button is pressed */
   @FXML
   void onKeyReleased(KeyEvent event) throws IOException {
      if (event.getCode().equals(KeyCode.ENTER)) {
         ActionEvent.fireEvent(event.getTarget(), new ActionEvent());
      }
   }

   /** Class representing a matching pair */
   @Data
   @AllArgsConstructor
   public static class MatchingPair {
      /** Key of match */
      private String key;
      /** Value of match */
      private String value;

   }

   /**
    * Table cell that display's a 'X' button for removing the current row
     */
   private static class RemoveCell<T, S> extends TableCell<T, S> {
      /** Button for removing cell */
      private final Button removeButton = new Button("X");

      public RemoveCell(final TableView<T> tableView) {
         removeButton.setOnAction(event -> {
            int rowIndex = getTableRow().getIndex();
            tableView.getItems().remove(rowIndex);
         });
      }

      /**
       * {@inheritDoc}
        */
      @Override
      protected void updateItem(S item, boolean empty) {
         super.updateItem(item, empty);

         if (!empty) {
            setContentDisplay(ContentDisplay.GRAPHIC_ONLY);
            setGraphic(removeButton);
         } else {
            setGraphic(null);
         }
      }
   }

}
