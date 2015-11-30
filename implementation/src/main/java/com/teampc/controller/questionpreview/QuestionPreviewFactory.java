package com.teampc.controller.questionpreview;

import com.teampc.model.question.Question;
import com.teampc.model.question.Question.QuestionType;

import java.util.Optional;

/**
 * Created by james on 11/30/15.
 */
public class QuestionPreviewFactory {

   public static Optional<QuestionPreviewController> getQuestionController(Question question) {
      QuestionType questionType = question.getType();

      switch (questionType) {
         case SHORT_ANSWER:
            return Optional.of(new ShortQuestionPreviewController(question));
         case MULTIPLE_CHOICE:
            return Optional.empty(); // todo:
         case MATCHING:
            return Optional.empty();
         case CODE:
            return Optional.empty();
         default:
            return Optional.empty();
      }
   }
}
