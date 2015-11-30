package com.teampc.controller.questionpreview;

import com.teampc.model.question.Question;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by james on 11/30/15.
 */
public class ShortQuestionPreviewController extends QuestionPreviewController {

   @FXML
   private TextField textResponse;

   @FXML
   private Text questionPrompt;

   public ShortQuestionPreviewController(Question question) {
      super(question);
   }

   @Override
   public void initialize(URL location, ResourceBundle resources) {
      questionPrompt.setText(question.getPrompt());
   }
}
