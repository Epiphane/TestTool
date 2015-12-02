package com.teampc.controller.question;

import com.teampc.model.question.Question;

import javafx.scene.control.Button;

public interface QuestionAction {
   void save(Question question);
   void saveAsNew(Question question);
   void displaySaveAsNewButton(Button saveAsNewButton);
   String getTitle();
}
