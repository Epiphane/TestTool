package com.teampc.controller.questionview;

import com.teampc.model.question.InvalidQuestionException;
import com.teampc.model.question.Question;
import com.teampc.model.testtaking.QuestionResponse;
import com.teampc.model.testtaking.ShortAnswerQuestionResponse;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.control.TextArea;
import javafx.scene.control.Toggle;
import javafx.scene.control.ToggleGroup;

import java.util.List;
import java.util.stream.IntStream;

/**
 * Created by adufrene on 11/30/15.
 */
public class ShortAnswerQuestionController extends QuestionViewController<ShortAnswerQuestionResponse> {

   @FXML
   private TextArea response;

   private ShortAnswerQuestionResponse.MatchType matchType;

   /**
    * Set up layout; match radio buttons on screen with appropriate data values
    */
   @FXML
   private void initialize() {
   }

   /**
    * Set the question for editing or answering
    */
   @Override
   public void setQuestion(Question<ShortAnswerQuestionResponse> question) throws NullPointerException {
      super.setQuestion(question);

      if (question == null) {
         throw new NullPointerException("Question is null");
      }

      if (question.getCorrectAnswer() == null) {
         throw new NullPointerException("Question has no correct answer");
      }

      matchType = question.getCorrectAnswer().getMatchType();
   }

   public void setResponse(ShortAnswerQuestionResponse response) {
      this.response.setText(response.getAnswer());
   }

   /**
    * Get the selected MC response
    */
   public ShortAnswerQuestionResponse getResponse() {
      return new ShortAnswerQuestionResponse(response.getText(), matchType);
   }
}
