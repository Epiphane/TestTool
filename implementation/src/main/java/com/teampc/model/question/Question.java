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
public abstract class Question<T extends QuestionResponse> {
   //The text prompt for the question.
   private String prompt;
   //the integer point value of this question.
   private int points;
   //a QuestionResponse object of the appropriate type
   private T correctAnswer;

   /**
    *
    * Assess and set the point value on a response to this question.
    * 
      pre: response != null && response.isComplete()
    * 
      post: response.getPoints() > 0
    */
   @SuppressWarnings("unchecked")
   public void grade(T response) {
      correctAnswer.assignPoints(response);
   }

   /**
    * Get the specific type of the question, used to display strings for question types
    * @return type of this question
    */
   public abstract QuestionTypeName getTypeName();

   public enum QuestionTypeName {
      CODE("Code"),
      MATCHING("Matching"),
      MULTIPLE_CHOICE("Multiple Choice"),
      SHORT_ANSWER("Short Answer");

      private String displayText;

      QuestionTypeName(String displayText) {
         this.displayText = displayText;
      }

      @Override
      public String toString() {
         return displayText;
      }
   }
}
