package com.teampc.controller.question;

import com.teampc.controller.test.CreateTestController;
import com.teampc.dao.QuestionDAO;
import com.teampc.model.question.Question;
import com.teampc.utils.FXUtils;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.util.Arrays;
import java.util.Objects;

import static java.util.stream.Collectors.toList;

/**
 * Created by adufrene on 11/9/15.
 *
 * Controller representing the question table layout
 */
@SuppressWarnings("unused")
@Slf4j
public class QuestionTableController {
   /** Primary stage of the app */
   @Setter
   private Stage primaryStage = null;
   /** Table of all questions */
   @FXML
   private TableView<Question> questionTable;

   /** Reference to question db acces object */
   private QuestionDAO questionDAO;

   /**
    * Opens the question table screen
    * @param stage the current screen to be replaced
    * @throws IOException
    */
   public static void showQuestionTable(Stage stage) throws IOException {
      FXUtils.newScreenAndConfigureController("question-table.fxml", QuestionTableController::setPrimaryStage);
   }

   /**
    * Setup layout, fetch saved questions from db and display
    */
   @FXML
   private void initialize() {
      questionDAO = QuestionDAO.getInstance();

      ObservableList<Question> questions = questionTable.getItems();
      questions.addAll(questionDAO.fetchAll().stream().filter(Objects::nonNull).collect(toList()));

      questionTable.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
      questionTable.setRowFactory(tv -> {
         TableRow<Question> row = new TableRow<>();
         row.setOnMouseClicked(event -> {
            if (event.getClickCount() > 1 && !row.isEmpty()) {
               Question<?> question = row.getItem();
               try {
                  QuestionEditController.openQuestionEdit(primaryStage, new EditAction(),
                     controller -> controller.editQuestion(question));
               } catch (IOException e) {
                  log.error("Error switching to edit question", e);
               }
            }
         });
         return row;
      });
   }

   /**
    * Opens new question screen
    */
   @FXML
   private void newQuestion() throws IOException {
      log.debug("New Question");
      QuestionEditController.openQuestionEdit(primaryStage, new NewAction(), FXUtils::noop);
   }

   /**
    * Opens to search question screen
    */
   @FXML
   private void searchQuestions() throws IOException {
      log.debug("Searching questions");
      FXUtils.newScreenAndConfigureController("question-bank-search.fxml", QuestionSearchController::setPrimaryStage);
   }

   /**
    * Opens new test screen, sending currently selected questions along
    */
   @FXML
   private void makeTest() throws IOException {
      ObservableList<Question> selectedItems = questionTable.getSelectionModel().getSelectedItems();
      if (selectedItems.isEmpty()) {
         new Alert(Alert.AlertType.WARNING, "Can't make test with no questions selected", ButtonType.CLOSE).show();
         log.debug("Trying to make a test with no questions... aborting");
         return;
      }

      FXUtils.switchToScreenAndConfigureController(primaryStage, "create-test-options.fxml",
         (CreateTestController controller, Stage stage) -> controller.setQuestions(selectedItems));
   }

    /**
     * Deletes the currently selected question
     */
   @FXML
   private void deleteSelectedQuestions() {
      ObservableList<Question> selectedItems = questionTable.getSelectionModel().getSelectedItems();
      if (selectedItems.isEmpty()) {
         return;
      }

      questionTable.getItems().removeAll(selectedItems);
      questionDAO.delete(selectedItems);
   }
}
