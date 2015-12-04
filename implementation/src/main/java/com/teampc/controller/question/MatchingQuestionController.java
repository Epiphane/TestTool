package com.teampc.controller.question;

import com.google.common.base.Strings;
import com.teampc.model.question.Question;
import com.teampc.model.testtaking.QuestionResponse;
import com.teampc.model.testtaking.MatchingQuestionResponse;
import javafx.fxml.FXML;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import lombok.AllArgsConstructor;
import lombok.Data;

import static java.util.stream.Collectors.toMap;

/**
 * Created by adufrene on 11/30/15.
 */
public class MatchingQuestionController implements QuestionTypeController<MatchingQuestionResponse> {

   @FXML
   private TableView<MatchingPair> options;

   @FXML
   private TextField keyField;

   @FXML
   private TextField valueField;

    /**
     * Setup screen, make table editable
     */
   @FXML
   private void initialize() {
      options.setEditable(true);
   }

   /**
    * Adds a new option to table of matching pairs
    */
   @FXML
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
   public Question<MatchingQuestionResponse> createQuestion(String prompt) {
      Question<MatchingQuestionResponse> question = new Question<>();
      question.setPrompt(prompt);
      question.setType(Question.QuestionType.MATCHING);
      question.setCorrectAnswer(new MatchingQuestionResponse(
         options.getItems().stream().collect(toMap(MatchingPair::getKey, MatchingPair::getValue))
      ));
      return question;
   }

   @Data
   @AllArgsConstructor
   public static class MatchingPair {
      private String key;
      private String value;

   }
}
