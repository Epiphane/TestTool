package com.teampc.controller.question;

import com.teampc.model.question.Question;
import com.teampc.dao.QuestionDAO;

import javafx.scene.control.Button;


/** Question Action for adding a new question */
public class NewAction implements QuestionAction {
   /**
    * {@inheritDoc}
     */
   public void save(Question question) {
      QuestionDAO.getInstance().insert(question);
   }

   /**
    * {@inheritDoc}
     */
   public void saveAsNew(Question question) {
      throw new UnsupportedOperationException();
   }

   /**
    * {@inheritDoc}
     */
   public void displaySaveAsNewButton(Button saveAsNewButton) {
      saveAsNewButton.setVisible(false);
   }

   /**
    * {@inheritDoc}
     */
   public String getTitle() {
      return "New Question";
   }
}
