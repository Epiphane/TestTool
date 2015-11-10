package com.teampc.controller;

import com.teampc.dao.QuestionDAO;
import com.teampc.model.question.Question;
import com.teampc.model.testtaking.QuestionResponse;
import com.teampc.utils.FXUtils;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableView;
import javafx.stage.Stage;
import lombok.Setter;

import java.io.IOException;

/**
 * Created by adufrene on 11/9/15.
 *
 */
@SuppressWarnings("unused")
public class QuestionTableController {
   @Setter
   private Stage primaryStage = null;
   @FXML
   private TableView<Question> questionTable;

   private QuestionDAO questionDAO;

   /**
    * 
    * @param stage
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

   @FXML
   private void newQuestion() {

      // Make fake question temporarily
      Question question = new Question() {
         @Override
         public void grade(QuestionResponse response) {

         }
      };

      question.setPoints(1);
      question.setPrompt("What is a question?");
      question.setCorrectAnswer(new QuestionResponse() {
         @Override
         public void isComplete() {

         }

         @Override
         public String toString() {
            return "Something";
         }
      });

      questionTable.getItems().add(question);
   }

   @FXML
   private void searchQuestions() {
   }

   @FXML
   private void makeTest() {
      ObservableList<Question> selectedItems = questionTable.getSelectionModel().getSelectedItems();
      if (selectedItems.isEmpty()) {
         return;
      }
   }
}
