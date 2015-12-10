package com.teampc.controller.questionview;

import com.teampc.view.RowView;
import com.google.common.base.Strings;
import com.teampc.model.question.Question;
import com.teampc.model.testtaking.QuestionResponse;
import com.teampc.model.testtaking.MatchingQuestionResponse;
import javafx.fxml.FXML;
import javafx.collections.*;
import javafx.scene.control.*;
import javafx.scene.input.*;
import javafx.scene.image.*;
import javafx.scene.text.*;
import java.util.stream.Collectors;
import java.util.*;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 *
 * Created by adufrene on 11/30/15.
 */
public class MatchingQuestionController extends QuestionViewController<MatchingQuestionResponse> {

   @FXML
   private ListView<RowView<MatchingRowController>> pairs;

   private ObservableList<RowView<MatchingRowController>> matches;
   private List<String> values;

   private static final String RESOURCE = "question/matching-row.fxml";

    /**
     * Setup screen, make table editable
     */
   @FXML
   private void initialize() {
      matches = FXCollections.observableArrayList();

      pairs.setItems(matches);
   }

   /**
    * Set the question for editing or answering
    */
   public void setQuestion(Question<MatchingQuestionResponse> question) {
      super.setQuestion(question);

      Map<String, String> pairings = question.getCorrectAnswer().getPairings();

      values = pairings.entrySet().stream().map(Map.Entry::getValue).collect(Collectors.toList());

      pairings.entrySet()
         .stream()
         .forEach(pair -> matches.add(new RowView<>(new MatchingRowController(pair.getKey(), values), RESOURCE)));
   }

   /**
    * Auto populate an existing response
    */
   public void setResponse(MatchingQuestionResponse response) {
      matches.clear();

      response.getPairings().entrySet()
         .stream()
         .forEach(pair -> matches.add(new RowView<>(new MatchingRowController(pair.getKey(), values, pair.getValue()), RESOURCE)));
   }

   /**
    * Get the selected MC response
    */
   public MatchingQuestionResponse getResponse() {
      Map<String, String> map = new HashMap<String, String>();
      matches.forEach(rowView -> rowView.getController().putInMap(map));

      return new MatchingQuestionResponse(map);
   }

   public void freeze() {
      pairs.setDisable(true);
      matches.stream().forEach(nextMatch -> nextMatch.getController().freeze());
   }
}
