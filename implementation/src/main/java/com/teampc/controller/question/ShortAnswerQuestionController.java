package com.teampc.controller.question;

import com.teampc.model.question.InvalidQuestionException;
import com.teampc.model.question.Question;
import com.teampc.model.testtaking.ShortAnswerQuestionResponse;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.control.Toggle;
import javafx.scene.control.ToggleGroup;

import java.util.List;
import java.util.Optional;
import java.util.stream.IntStream;

/**
 * Created by adufrene on 11/30/15.
 *
 * Controller for short answer question screen
 */
public class ShortAnswerQuestionController implements QuestionTypeController<ShortAnswerQuestionResponse> {

   /** List of supplied keywords for grading */
   @FXML
   private TextField keywords;

   /** Toggle group for what algorithm we want to match our keywords with */
   @FXML
   private ToggleGroup matchGroup;

   /**
    * Set up layout; match radio buttons on screen with appropriate data values
    */
   @FXML
   @SuppressWarnings("unused")
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
   public Question<ShortAnswerQuestionResponse> createQuestion(String prompt, Optional<Integer> questionId) throws InvalidQuestionException {
      Question<ShortAnswerQuestionResponse> question = new Question<>();
      question.setPrompt(prompt);
      question.setType(Question.QuestionType.SHORT_ANSWER);
      question.setCorrectAnswer(
         new ShortAnswerQuestionResponse(
            keywords.getCharacters().toString(),
            (ShortAnswerQuestionResponse.MatchType) matchGroup.getSelectedToggle().getUserData()
         )
      );
      questionId.ifPresent(question::setId);
      return question;
   }

   /**
    * {@inheritDoc}
     */
   @Override
   public void setQuestion(ShortAnswerQuestionResponse questionResponse) {
      keywords.setText(questionResponse.getAnswer());
      matchGroup.getToggles().stream()
         .filter(toggle -> toggle.getUserData().equals(questionResponse.getMatchType()))
         .findAny()
         .ifPresent(toggle -> toggle.setSelected(true));
   }
}
