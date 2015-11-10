package com.teampc.model.question;

import com.teampc.model.testtaking.QuestionResponse;
import lombok.Getter;
import lombok.Setter;

/**
 * This class represents a coding question.
 * @author David Ellison
 */
@Getter
@Setter
public class CodeQuestion extends Question {

   //TODO 10-21-2015: Figure out how grading scripts will work.
   //The raw text of the grading script that answers will be evaluated against.
   private String gradingScriptText;


   @Override
   public void grade(QuestionResponse response) {

   }

   @Override
   public QuestionTypeName getTypeName() {
      return null;
   }
}
