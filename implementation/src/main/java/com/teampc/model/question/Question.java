package com.teampc.model.question;

import lombok.AllArgsConstructor;
import lombok.Data;

import com.teampc.model.testtaking.*;
import lombok.NoArgsConstructor;
import com.teampc.dao.definitions.question.QuestionDD;
import com.teampc.dao.definitions.response.QuestionResponseDD;
import com.teampc.model.Model;

import lombok.extern.slf4j.Slf4j;

/**
 * A class that represents a test question.
 *
 * @author David Ellison
 */

@Data
@Slf4j
public class Question<T extends QuestionResponse> implements Model<QuestionDD> {

   /**
    * an integer rating for the difficulty of the question
    */
   private int difficulty;

   /**
    * A unique identifier for the question.
    */
   private Integer id;

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

   public String toString() {
      return "Question: " + prompt + " Answer: " + getCorrectAnswer().toString();
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

   public QuestionDD asEntity() {
      log.debug("Transforming this question to entity.");
      log.debug("" + this);

      QuestionDD question = new QuestionDD();
      question.setId(id);
      question.setPoints(points);
      question.setDifficulty(difficulty);
      question.setPrompt(prompt);
      question.setType(type);

      question.setCorrectAnswer((QuestionResponseDD) correctAnswer.asEntity());
      question.getCorrectAnswer().setQuestion(question);
      return question;
   }
}
