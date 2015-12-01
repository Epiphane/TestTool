package com.teampc.controller.questionpreview;

import com.teampc.model.question.Question;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.text.Text;

public abstract class QuestionPreviewController implements Initializable {

   @FXML
   /** Text field for the question **/
   private Text questionPrompt;

   /** The question that is displayed in the view **/
   protected Question question;

   public QuestionPreviewController(Question question) {
      this.question = question;
   }
}
