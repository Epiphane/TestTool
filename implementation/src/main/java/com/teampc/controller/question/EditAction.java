package com.teampc.controller.question;

import com.teampc.model.question.Question;
import com.teampc.dao.QuestionDAO;

import javafx.scene.control.Button;

/**
 * Question action for editing a question
 */
public class EditAction implements QuestionAction {
   /**
    * {@inheritDoc}
     */
   public void save(Question question) {
      QuestionDAO.getInstance().update(question);
   }

   /**
    * {@inheritDoc}
     */
   public void saveAsNew(Question question) {
      QuestionDAO.getInstance().insert(question);
   }

   /**
    * {@inheritDoc}
     */
   public void displaySaveAsNewButton(Button saveAsNewButton) {
      saveAsNewButton.setVisible(true);
   }

   /**
    * {@inheritDoc}
     */
   public String getTitle() {
      return "Edit Question";
   }
}
