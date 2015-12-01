package com.teampc.controller;

import com.teampc.dao.QuestionDAO;
import com.teampc.model.question.MultipleChoiceQuestion;
import com.teampc.model.question.Question;
import com.teampc.model.question.ShortAnswerQuestion;
import com.teampc.model.testtaking.MultipleChoiceQuestionResponse;
import com.teampc.model.testtaking.QuestionResponse;
import com.teampc.utils.FXUtils;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableView;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.util.Arrays;

/**
 * Created by adufrene on 11/9/15.
 *
 */
@SuppressWarnings("unused")
@Slf4j
public class QuestionTableController {
   @Setter
   private Stage primaryStage = null;
   @FXML
   private TableView<Question> questionTable;

   private QuestionDAO questionDAO;

   /**
    * Opens the question table screen
    * @param stage the current screen to be replaced
    * @throws IOException
    */
   public static void showQuestionTable(Stage stage) throws IOException {
      FXUtils.newScreenAndConfigureController("question-table.fxml", QuestionTableController::setPrimaryStage);
   }

   @FXML
   private void initialize() {
      questionDAO = QuestionDAO.getInstance();

      ObservableList<Question> questions = questionTable.getItems();
      questions.addAll(questionDAO.fetchAll());

      questionTable.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
   }

   /**
    * Opens new question screen
    */
   @FXML
   private void newQuestion() {

      // Make fake question temporarily
      ShortAnswerQuestion question = new ShortAnswerQuestion();

      question.setPoints(1);
      question.setPrompt("What is a question?");
      question.setCorrectAnswer(new MultipleChoiceQuestionResponse(2));

      questionDAO.insert(question);
      questionTable.getItems().add(question);

      log.debug("New Question");
   }

   /**
    * Opens to search question screen
    */
   @FXML
   private void searchQuestions() throws IOException {
      log.debug("Searching questions");
      FXUtils.newScreenAndConfigureController("question-bank-search.fxml", FXUtils::noop);
   }
   /**
    * Opens new test screen, sending currently selected questions along
    */
   @FXML
   private void makeTest() {
      ObservableList<Question> selectedItems = questionTable.getSelectionModel().getSelectedItems();
      if (selectedItems.isEmpty()) {
         log.debug("Trying to make a test with no questions... aborting");
         return;
      }
      log.debug("Making test with selected questions: " + Arrays.toString(selectedItems.toArray(new Question[selectedItems.size()])));
   }
}
