package com.teampc.controller.question;

import com.teampc.model.question.InvalidQuestionException;
import com.teampc.model.question.Question;
import com.teampc.model.testtaking.ShortAnswerQuestionResponse;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.control.Toggle;
import javafx.scene.control.ToggleGroup;

import java.util.List;
import java.util.stream.IntStream;

/**
 * Created by adufrene on 11/30/15.
 */
public class ShortAnswerQuestionController implements QuestionTypeController<ShortAnswerQuestionResponse> {

   @FXML
   private TextField keywords;

   @FXML
   private ToggleGroup matchGroup;

   private ShortAnswerQuestionResponse.MatchType matchType;

   /**
    * Set up layout; match radio buttons on screen with appropriate data values
    */
   @FXML
   private void initialize() {
      List<Toggle> toggles = matchGroup.getToggles();
      ShortAnswerQuestionResponse.MatchType values[] = ShortAnswerQuestionResponse.MatchType.values();
      IntStream.range(0, Math.min(toggles.size(), values.length)).forEach(index -> {
         toggles.get(index).setUserData(values[index]);
      });
   }

   /**
    * {@inheritDoc}
     */
   @Override
   public Question<ShortAnswerQuestionResponse> createQuestion(String prompt) throws InvalidQuestionException {
      Question<ShortAnswerQuestionResponse> question = new Question<>();
      question.setPrompt(prompt);
      question.setType(Question.QuestionType.SHORT_ANSWER);
      question.setCorrectAnswer(new ShortAnswerQuestionResponse(keywords.getCharacters().toString(), (ShortAnswerQuestionResponse.MatchType) matchGroup.getSelectedToggle().getUserData()));
      return question;
   }
}
