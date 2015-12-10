package com.teampc.controller.question;

import com.teampc.model.question.InvalidQuestionException;
import com.teampc.model.question.Question;
import com.teampc.model.testtaking.CodeQuestionResponse;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.util.Optional;

/**
 * Created by adufrene on 11/30/15.
 *
 * Controller for code question screen
 */
@Slf4j
public class CodeQuestionController implements QuestionTypeController<CodeQuestionResponse> {

   /** Reference to primary stage */
   @Setter
   private Stage primaryStage;

   /** Text area for inputted code */
   @FXML
   private TextArea givenCode;

   /** button to upload grading script */
   @FXML
   private Button uploadButton;

   /** Grading file, present means we've set the file, not present means file hasn't been set */
   private Optional<File> gradingScriptFile = Optional.empty();

   /**
    * Upload grading script for grading a code question.
    */
   @FXML
   @SuppressWarnings("unused")
   private void getGradingScript() {
      log.debug("Get Grading Script");
   }

   /**
    * {@inheritDoc}
     */
   @Override
   public Question<CodeQuestionResponse> createQuestion(String prompt, Optional<Integer> questionId) throws InvalidQuestionException {
      Question<CodeQuestionResponse> question = new Question<>();
      if (!gradingScriptFile.map(file -> file.isFile() && file.canExecute()).orElse(false)) {
         throw new InvalidQuestionException("Missing or invalid grading script");
      }

      question.setCorrectAnswer(new CodeQuestionResponse(gradingScriptFile.get().getAbsolutePath(), givenCode.getText()));
      questionId.ifPresent(question::setId);
      return question;
   }

   /**
    * {@inheritDoc}
     */
   @Override
   public void setQuestion(CodeQuestionResponse questionResponse) {
      File gradingFile = new File(questionResponse.getCodeAnswer());
      uploadButton.setText(gradingFile.getName());
      gradingScriptFile = Optional.of(gradingFile);
      givenCode.setText(questionResponse.getGivenCode());
   }

   /**
    * Open file chooser to select a grading script file
    */
   @FXML
   @SuppressWarnings("unused")
   private void selectGradingFile() {
      FileChooser gradingFileChooser = new FileChooser();
      gradingFileChooser.setTitle("Select grading program");
      File uploadedFile = gradingFileChooser.showOpenDialog(primaryStage);
      uploadButton.setText(uploadedFile.getName());
      gradingScriptFile = Optional.of(uploadedFile);
   }
}
