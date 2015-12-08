package com.teampc.controller.questionpreview;

import com.teampc.model.question.Question;
import com.teampc.model.question.Question.QuestionType;

import java.util.Optional;

/**
 * Created by james on 11/30/15.
 */
public class QuestionPreviewFactory {

   /** Returns a QuestionController given a Question **/
   public static Optional<QuestionPreviewController> getQuestionController(Question question) {
      return question.getType().accept(new Question.QuestionTypeVisitor<Optional<QuestionPreviewController>>() {
         @Override
         public Optional<QuestionPreviewController> visitCode() {
            return Optional.of(new ShortQuestionPreviewController(question));
         }

         @Override
         public Optional<QuestionPreviewController> visitMatching() {
            return Optional.empty();
         }

         @Override
         public Optional<QuestionPreviewController> visitMultipleChoice() {
            return Optional.empty();
         }

         @Override
         public Optional<QuestionPreviewController> visitShortAnswer() {
            return Optional.empty();
         }
      });
   }
}
