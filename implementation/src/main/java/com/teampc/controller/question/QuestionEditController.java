package com.teampc.controller.question;

import com.teampc.model.question.InvalidQuestionException;
import com.teampc.model.question.Question;
import com.teampc.model.testtaking.QuestionResponse;
import com.teampc.utils.FXUtils;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import lombok.NonNull;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.util.Optional;
import java.util.function.Consumer;

/**
 * Created by adufrene on 11/30/15.
 *
 * Controller used for editing or creating new questions
 */
@Slf4j
public class QuestionEditController {

   /** Currently selected question type */
   @FXML
   private ComboBox<Question.QuestionType> questionType;

   /** Reference to code question layout */
   @FXML
   private Node codeNode;

   /** Reference to code question layout controller */
   @FXML
   private CodeQuestionController codeNodeController;

   /** Reference to matching question layout */
   @FXML
   private Node matchingNode;

   /** Reference to matching question layout controller */
   @FXML
   private MatchingQuestionController matchingNodeController;

   /** Reference to multiple choice layout */
   @FXML
   private Node multipleChoiceNode;

   /** Reference to multiple choice layout controller */
   @FXML
   private MultipleChoiceQuestionController multipleChoiceNodeController;

   /** Reference to short answer layout */
   @FXML
   private Node shortAnswerNode;

   /** Reference to short answer layout controlle */
   @FXML
   private ShortAnswerQuestionController shortAnswerNodeController;

   /** Field for question prompt */
   @FXML
   private TextField prompt;

   /** Button to save question as new question, dependent on set question action */
   @FXML
   private Button saveAsNewButton;

   /** Title of screen */
   @FXML
   private Label title;

   /** Function for exiting the screen, usually returns to Question table */
   @Setter
   @NonNull
   private ExitFunction exitFunction = this::returnToQuestionTable;

   /** Current controller on screen */
   private Optional<QuestionTypeController> currentController = Optional.empty();

   /** Primary stage in app */
   private Stage primaryStage;
   /** Question action determining what actions to take */
   private QuestionAction questionAction;
   /** Optional question id, set if we are editing a question and need to update the old question */
   private Optional<Integer> questionId = Optional.empty();

   /**
    * Visitor to get view node of corresponding question type
    */
   private Question.QuestionTypeVisitor<Node> nodeQuestionTypeVisitor = new Question.QuestionTypeVisitor<Node>() {
      @Override
      public Node visitCode() {
         return codeNode;
      }

      @Override
      public Node visitMatching() {
         return matchingNode;
      }

      @Override
      public Node visitMultipleChoice() {
         return multipleChoiceNode;
      }

      @Override
      public Node visitShortAnswer() {
         return shortAnswerNode;
      }
   };

   /**
    * Visitor to get controller of corresponding question type
    */
   private Question.QuestionTypeVisitor<QuestionTypeController> controllerQuestionTypeVisitor = new Question.QuestionTypeVisitor<QuestionTypeController>() {
      @Override
      public QuestionTypeController visitCode() {
         return codeNodeController;
      }

      @Override
      public QuestionTypeController visitMatching() {
         return matchingNodeController;
      }

      @Override
      public QuestionTypeController visitMultipleChoice() {
         return multipleChoiceNodeController;
      }

      @Override
      public QuestionTypeController visitShortAnswer() {
         return shortAnswerNodeController;
      }
   };

    /**
     * Open Question Edit/New Screen
     * @throws IOException
     */
   public static void openQuestionEdit(Stage primaryStage,
                                       QuestionAction questionAction,
                                       Consumer<QuestionEditController> configurationFunc) throws IOException {
      FXUtils.switchToScreenAndConfigureController(primaryStage, "question-edit-main.fxml",
         (QuestionEditController controller, Stage stage) -> {
            controller.setPrimaryStage(stage);
            controller.setQuestionAction(questionAction);
            configurationFunc.accept(controller);
         });
   }

    /**
     * Begin edit question flow
     */
   public <T extends QuestionResponse> void editQuestion(Question<T> question) {
      questionType.getSelectionModel().select(question.getType());
      currentController.ifPresent(controller -> controller.setQuestion(question.getCorrectAnswer()));
      questionId = Optional.of(question.getId());
      prompt.setText(question.getPrompt());
   }

   /**
    * Set up question screen, mostly hiding or showing appropriate layouts
    */
   @FXML
   @SuppressWarnings("unused")
   private void initialize() {
      questionType.setItems(FXCollections.observableArrayList(Question.QuestionType.values()));
      questionType.getSelectionModel().selectedItemProperty().addListener((selected, oldType, newType) -> {
         Optional.ofNullable(oldType).map(type -> type.accept(nodeQuestionTypeVisitor)).ifPresent(node -> node.setVisible(false));
         newType.accept(nodeQuestionTypeVisitor).setVisible(true);

         currentController = Optional.of(newType.accept(controllerQuestionTypeVisitor));
      });


      codeNode.setVisible(false);
      matchingNode.setVisible(false);
      multipleChoiceNode.setVisible(false);
      shortAnswerNode.setVisible(false);
   }

   /**
    * Set the questionAction, perform ui configuration from new action
    */
   public void setQuestionAction(QuestionAction questionAction) {
      if (questionAction == null) {
         throw new NullPointerException("Question Action cannot be null");
      }
      this.questionAction = questionAction;
      configureFromAction();
   }

    /**
     * Set primary stage, and set nested controllers
     */
   public void setPrimaryStage(Stage primaryStage) {
      if (primaryStage == null) {
         throw new NullPointerException("Primary Stage cannot be null");
      }
      this.primaryStage = primaryStage;
      codeNodeController.setPrimaryStage(primaryStage);
   }

   /**
    * Configure based on question action
    */
   private void configureFromAction() {
      questionAction.displaySaveAsNewButton(saveAsNewButton);
      title.setText(questionAction.getTitle());
   }

    /**
     * Save question or update question depending on if this is a new or existing question
     */
   @FXML
   @SuppressWarnings("unused")
   private void saveQuestion() throws IOException {
      saveQuestionUsingSaver(questionAction::save, questionId);
   }

   /**
    * Save question as new question
    */
   @FXML
   @SuppressWarnings("unused")
   private void saveAsNewQuestion() throws IOException {
      saveQuestionUsingSaver(questionAction::saveAsNew, Optional.empty());
   }

   /**
    * Callback for when enter key is pressed
     */
   @FXML
   void onKeyReleased(KeyEvent event) throws IOException {
      if (event.getCode().equals(KeyCode.ENTER)) {
         ActionEvent.fireEvent(event.getTarget(), new ActionEvent());
      }
   }

   /**
    * Save question using supplied questionSaver consumer, then exit screen
    */
   private void saveQuestionUsingSaver(Consumer<Question> questionSaver, Optional<Integer> maybeId) throws IOException {
      currentController.ifPresent(controller -> {
         try {
            Question question = controller.createQuestion(prompt.getCharacters().toString(), maybeId);
            questionSaver.accept(question);
            exitFunction.exit();
         } catch (InvalidQuestionException e) {
            log.error("Error creating question, not saving", e);
            Alert alert = new Alert(Alert.AlertType.ERROR, "Error saving question: " + e.getMessage(), ButtonType.CLOSE);
            alert.show();
         } catch (IOException e) {
            log.error("Error returning to question table screen");
         }
      });
   }

   /**
    * return to question table screen
    * @throws IOException
     */
   private void returnToQuestionTable() throws IOException {
      FXUtils.switchToScreenAndConfigureController(primaryStage, "question-table.fxml", QuestionTableController::setPrimaryStage);
   }

   /**
     * Cancel the question creation process and return to the table
     */
   @FXML
   @SuppressWarnings("unused")
   private void cancel() throws IOException {
      exitFunction.exit();
   }

   /** Interface for providing a function to exit the screen */
   @FunctionalInterface
   public interface ExitFunction {
      void exit() throws IOException;
   }

}
