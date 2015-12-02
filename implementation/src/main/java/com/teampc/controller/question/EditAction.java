package com.teampc.controller.question;

import com.teampc.model.question.Question;
import com.teampc.dao.QuestionDAO;

import javafx.scene.control.Button;

public class EditAction implements QuestionAction {
   public void save(Question question) {
//      QuestionDAO.getInstance().update(question);
   }

   public void saveAsNew(Question question) {
      QuestionDAO.getInstance().insert(question);
   }

   public void displaySaveAsNewButton(Button saveAsNewButton) {
      saveAsNewButton.setVisible(true);
   }

   public String getTitle() {
      return "Edit Question";
   }
}
