package com.teampc.controller.question;

import com.teampc.model.question.Question;
import com.teampc.dao.QuestionDAO;

import javafx.scene.control.Button;


public class NewAction implements QuestionAction {
   public void save(Question question) {
      QuestionDAO.getInstance().insert(question);
   }

   public void saveAsNew(Question question) {
      throw new UnsupportedOperationException();
   }

   public void displaySaveAsNewButton(Button saveAsNewButton) {
      saveAsNewButton.setVisible(false);
   }

   public String getTitle() {
      return "New Question";
   }
}
