package com.teampc.controller.question;

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
public class CodeQuestionController implements QuestionTypeController<CodeQuestionResponse> {

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
    * {@inheritDoc}
     */
   @Override
   public Question<CodeQuestionResponse> createQuestion(String prompt) {
      Question<CodeQuestionResponse> question = new Question<>();
      question.setType(Question.QuestionType.CODE);
      question.setPrompt(prompt);
      question.setCorrectAnswer(new CodeQuestionResponse("1"));
//      question.setCorrectAnswer(new CodeQuestionResponse(gradingScriptFile.filter(File::isFile)
//         .map(FileUtils::readFileToString).orElseThrow(() -> new InvalidQuestionException("Missing or invalid grading script"))));
      return question;
   }
}
