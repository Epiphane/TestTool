package com.teampc.controller.question;

import com.teampc.dao.QuestionDAO;
import com.teampc.model.question.InvalidQuestionException;
import com.teampc.model.question.Question;
import com.teampc.model.testtaking.QuestionResponse;
import com.teampc.model.testtaking.ShortAnswerQuestionResponse;
import com.teampc.utils.FXUtils;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.util.Optional;

/**
 * Created by adufrene on 11/30/15.
 */
@Slf4j
public class QuestionEditController {

   @FXML
   private ComboBox<Question.QuestionType> questionType;

   @FXML
   private Node codeNode;

   @FXML
   private CodeQuestionController codeNodeController;

   @FXML
   private Node matchingNode;

   @FXML
   private MatchingQuestionController matchingNodeController;

   @FXML
   private Node multipleChoiceNode;

   @FXML
   private MultipleChoiceQuestionController multipleChoiceNodeController;

   @FXML
   private Node shortAnswerNode;

   @FXML
   private ShortAnswerQuestionController shortAnswerNodeController;

   @FXML
   private TextField prompt;

   private Optional<QuestionTypeController> currentController = Optional.empty();

   @Setter
   private Stage primaryStage;

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

   @FXML
   /**
    * Set up question screen, mostly hiding or showing appropriate layouts
    */
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
     * Save question, filling appropriate fields based on type of question
     */
   @FXML
   private void saveQuestion() throws IOException {
      currentController.ifPresent(controller -> {
         try {
            Question question = controller.createQuestion(prompt.getCharacters().toString());
            QuestionDAO.getInstance().insert(question);
         } catch (InvalidQuestionException e) {
            log.error("Error creating question, not saving", e);
         }
      });
      FXUtils.switchToScreenAndConfigureController(primaryStage, "question-table.fxml", QuestionTableController::setPrimaryStage);
   }

    /**
     * Cancel the question creation process and return to the table
     */
   @FXML
   private void cancel() throws IOException {
      FXUtils.switchToScreenAndConfigureController(primaryStage, "question-table.fxml", QuestionTableController::setPrimaryStage);
   }

}
