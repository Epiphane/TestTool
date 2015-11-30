package com.teampc.controller.questionpreview;

import com.teampc.model.question.Question;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.text.Text;

public abstract class QuestionPreviewController implements Initializable {

   @FXML
   private Text questionPrompt;

   protected Question question;

   public QuestionPreviewController(Question question) {
      this.question = question;
   }
}
