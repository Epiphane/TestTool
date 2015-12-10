package com.teampc.controller.question;

import com.teampc.model.question.Question;

import javafx.scene.control.Button;

/** Interface for performing actions on questions w/in the QuestionEditController */
public interface QuestionAction {
   /**
    * Save supplied question, without worrying if new question will be created
     */
   void save(Question question);

    /**
     * Save supplied question as a new question, effectively guaranteeing an insert operation for the question
     */
   void saveAsNew(Question question);

    /**
     * Decide how and if we should display the provided "Save As New" button
     */
   void displaySaveAsNewButton(Button saveAsNewButton);

    /**
     * Return the title to be displayed for the question manipulation screen
     */
   String getTitle();
}
