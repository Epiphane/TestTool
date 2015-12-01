package com.teampc.model.question;

import lombok.Data;

import com.teampc.model.testtaking.*;

/**
 * A class that represents a test question.
 * Different question types extend this type.
 *
 * @author David Ellison
 */

@Data
public class Question<T extends QuestionResponse> {
   /**
    * The text prompt for the question.
    */
   private String prompt = "";
   /**
    * The integer point value of this question.
    */
   private int points = 1;

   /** a QuestionResponse object of the appropriate type */
   private T correctAnswer;

   /** the type of the question */
   private QuestionType type;

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
   public void grade(T response) {
      correctAnswer.assignPoints(response, points);
   }


   /**
    * types of allowed questions
    */
   public enum QuestionType {
      CODE("Code", CodeQuestionResponse.class) {
         @Override
         public <T> T accept(QuestionTypeVisitor<T> visitor) {
            return visitor.visitCode();
         }
      },
      MATCHING("Matching", MatchingQuestionResponse.class) {
         @Override
         public <T> T accept(QuestionTypeVisitor<T> visitor) {
            return visitor.visitMatching();
         }
      },
      MULTIPLE_CHOICE("Multiple Choice", MultipleChoiceQuestionResponse.class) {
         @Override
         public <T> T accept(QuestionTypeVisitor<T> visitor) {
            return visitor.visitMultipleChoice();
         }
      },
      SHORT_ANSWER("Short Answer", ShortAnswerQuestionResponse.class) {
         @Override
         public <T> T accept(QuestionTypeVisitor<T> visitor) {
            return visitor.visitShortAnswer();
         }
      };

      private String displayText;
      private Class responseClass;

      QuestionType(String displayText, Class responseClass) {
         this.displayText = displayText;
         this.responseClass = responseClass;
      }

       /**
        * visitor based on type of enum, used as polymorphic replacement of switch on enum
        */
      public abstract <T> T accept(QuestionTypeVisitor<T> visitor);

      @Override
      public String toString() {
         return displayText;
      }

   }

   public interface QuestionTypeVisitor<T> {
      T visitCode();
      T visitMatching();
      T visitMultipleChoice();
      T visitShortAnswer();
   }

}
