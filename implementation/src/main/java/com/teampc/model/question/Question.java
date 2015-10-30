package com.teampc.model.question;

import lombok.Getter;
import lombok.Setter;

import com.teampc.model.testtaking.QuestionResponse;

/**
 * A class that represents a test question.
 * Different question types extend this type.
 *
 * @author David Ellison
 */

//TODO 10-21-15: Make this class generic on <T extends QuestionResponse>.
@Getter
@Setter
public abstract class Question {
   //The text prompt for the question.
   private String prompt;
   //the integer point value of this question.
   private int points;
   //a QuestionResponse object of the appropriate type
   private QuestionResponse correctAnswer;

   /**
    * Assess and set the point value on a response to this question.
    */
   public abstract void grade(QuestionResponse response);

}
