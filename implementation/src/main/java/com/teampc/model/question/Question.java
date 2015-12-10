package com.teampc.model.question;

import com.teampc.dao.HasId;
import lombok.AllArgsConstructor;
import lombok.Data;

import com.teampc.model.testtaking.*;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * Question represents a test question.
 * Different question types extend this type.
 *
 * @author David Ellison
 */

@Data
@Slf4j
@AllArgsConstructor
@NoArgsConstructor
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

   /**
    * Copy constructor
    */
   @SuppressWarnings("unchecked")
   public Question(Question<T> question) {
      this.id = question.id;
      this.prompt = question.prompt;
      this.points = question.points;
      this.correctAnswer = (T) question.correctAnswer.copy();
      this.type = question.type;
   }

   @SuppressWarnings("unchecked")
   /**
    *
    * Assess and set the point value on a response to this question.
    *
    * @param response The response to the Question
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

      /**
       *
       * @return The display text of the string
       */
      @Override
      public String toString() {
         return displayText;
      }

      /**
       *
       * @return the string representing the file
       */
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


   /**
    * Makes a new response to a question
    * @return The newly created response
    */
   public QuestionResponse createResponse() {
      QuestionResponse result = this.getType().createResponse();

      result.setQuestion(this);

      return result;
   }
}
