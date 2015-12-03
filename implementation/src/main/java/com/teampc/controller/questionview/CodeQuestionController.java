package com.teampc.controller.questionview;

import com.teampc.model.question.Question;
import com.teampc.model.testtaking.CodeQuestionResponse;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import lombok.extern.slf4j.Slf4j;

import java.io.File;
import java.util.Optional;

/**
 * Created by adufrene on 11/30/15.
 */
@Slf4j
public class CodeQuestionController extends QuestionViewController<CodeQuestionResponse> {

   @FXML
   private TextArea givenCode;

   private Optional<File> gradingScriptFile = Optional.empty();

   /**
    * Upload grading script for grading a code question.
    */
   @FXML
   private void getGradingScript() {
      log.debug("Get Grading Script");
   }

   /**
    * Set the question for editing or answering
    */
   // public void setQuestion(Question question) {
   //    // matchType = question.getCorrectAnswer().getMatchType();
   // }

   /**
    * Auto populate an existing response
    */
   public void setResponse(CodeQuestionResponse response) {
      // keywords
   }

   /**
    * Get the selected MC response
    */
   public CodeQuestionResponse getResponse() {
      return new CodeQuestionResponse(givenCode.getText());
   }
}
