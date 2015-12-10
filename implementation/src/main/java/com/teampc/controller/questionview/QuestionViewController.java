package com.teampc.controller.questionview;

import com.teampc.model.question.Question;
import com.teampc.model.testtaking.QuestionResponse;
import javafx.fxml.FXML;
import javafx.scene.text.Text;

/**
 * Created by adufrene on 11/30/15.
 *
 * An interface for being able to create questions of a certain type
 */
public abstract class QuestionViewController<T extends QuestionResponse> {

   @FXML
   private Text prompt;

   public abstract T getResponse();

   public void setQuestion(Question<T> question) {
      if (prompt != null) {
         prompt.setText(question.getPrompt());
      }
   }

   public abstract void freeze();

   public abstract void setResponse(T response);
}
