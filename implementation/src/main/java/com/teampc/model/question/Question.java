package com.teampc.model.question;

import com.teampc.dao.HasId;
import lombok.Data;

import com.teampc.model.testtaking.*;
import lombok.extern.slf4j.Slf4j;

/**
 * A class that represents a test question.
 * Different question types extend this type.
 *
 * @author David Ellison
 */

@Data
@Slf4j
public class Question<T extends QuestionResponse> implements HasId {

   private int id;

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
      CODE("Code", CodeQuestionResponse.class, "code") {
         @Override
         public <T> T accept(QuestionTypeVisitor<T> visitor) {
            return visitor.visitCode();
         }
      },
      MATCHING("Matching", MatchingQuestionResponse.class, "matching") {
         @Override
         public <T> T accept(QuestionTypeVisitor<T> visitor) {
            return visitor.visitMatching();
         }
      },
      MULTIPLE_CHOICE("Multiple Choice", MultipleChoiceQuestionResponse.class, "multiple-choice") {
         @Override
         public <T> T accept(QuestionTypeVisitor<T> visitor) {
            return visitor.visitMultipleChoice();
         }
      },
      SHORT_ANSWER("Short Answer", ShortAnswerQuestionResponse.class, "short-answer") {
         @Override
         public <T> T accept(QuestionTypeVisitor<T> visitor) {
            return visitor.visitShortAnswer();
         }
      };

      private String displayText;
      private String fileString;
      private Class responseClass;

      QuestionType(String displayText, Class responseClass, String fileString) {
         this.displayText = displayText;
         this.responseClass = responseClass;
         this.fileString = fileString;
      }

       /**
        * visitor based on type of enum, used as polymorphic replacement of switch on enum
        */
      public abstract <T> T accept(QuestionTypeVisitor<T> visitor);

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
            log.error("Something went wrong with creation", e);
            return null;
         }
      }
   }

   public interface QuestionTypeVisitor<T> {
      T visitCode();
      T visitMatching();
      T visitMultipleChoice();
      T visitShortAnswer();
   }


   public QuestionResponse createResponse() {
      QuestionResponse result = this.getType().createResponse();

      result.setQuestion(this);

      return result;
   }
}
