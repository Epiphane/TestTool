package com.teampc.model.question;

import lombok.Getter;
import lombok.Setter;

import com.teampc.model.testtaking.*;

/**
 * A class that represents a test question.
 * Different question types extend this type.
 *
 * @author David Ellison
 */

//TODO 10-21-15: Make this class generic on <T extends QuestionResponse>.
@Getter
@Setter
public abstract class Question/*<T extends QuestionResponse>*/ {
   //The text prompt for the question.
   private String prompt;
   //the integer point value of this question.
   private int points;
   //a QuestionResponse object of the appropriate type
   private QuestionResponse correctAnswer;

   @SuppressWarnings("unchecked")
   /**
    *
    * Assess and set the point value on a response to this question.
    *
      pre: response != null && response.isComplete()
    *
      post: response.getPointsReceived() >= 0
    *
    */
   public void grade(QuestionResponse response) {
      correctAnswer.assignPoints(response);
   }

   /**
    * Get the specific type of the question, used to display strings for question types
    * @return type of this question
    */
   public abstract QuestionType getType();

   public enum QuestionType {
      CODE("Code", CodeQuestionResponse.class, "code"),
      MATCHING("Matching", MatchingQuestionResponse.class, "matching"),
      MULTIPLE_CHOICE("Multiple Choice", MultipleChoiceQuestionResponse.class, "multiple-choice"),
      SHORT_ANSWER("Short Answer", ShortAnswerQuestionResponse.class, "short-answer");

      private String displayText;
      private String fileString;
      private Class responseClass;

      QuestionType(String displayText, Class responseClass, String fileString) {
         this.displayText = displayText;
         this.responseClass = responseClass;
         this.fileString = fileString;
      }

      @Override
      public String toString() {
         return displayText;
      }

      public String getFileString() {
         return fileString;
      }

      public QuestionResponse createResponse() {
         try {
            return (QuestionResponse) responseClass.getConstructor().newInstance(new Object[] {});
         }
         catch (Exception e) {
            // Should never happen
            System.out.println("Error creating response for question type: " + this.toString());
            return null;
         }
      }
   }

   public QuestionResponse createResponse() {
      QuestionResponse response = this.getType().createResponse();
      response.setQuestion(this);
      return response;
   }
}
