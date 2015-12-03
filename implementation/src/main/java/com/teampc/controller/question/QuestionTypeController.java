package com.teampc.controller.question;

import com.teampc.model.question.InvalidQuestionException;
import com.teampc.model.question.Question;
import com.teampc.model.testtaking.QuestionResponse;
import javafx.fxml.FXML;
import javafx.scene.text.Text;
import javafx.scene.control.TextField;

/**
 * Created by adufrene on 11/30/15.
 *
 * An interface for being able to create questions of a certain type
 */
public interface QuestionTypeController<T extends QuestionResponse> {

   /**
    * Create a question given a prompt and using supplementary information from the controller.
     */
   public abstract Question<T> createQuestion(String prompt) throws InvalidQuestionException;
}
