package com.teampc.model.question;

import com.teampc.model.testtaking.QuestionResponse;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;

/**
 * This class represents a multiple choice question.
 *
 * @author David Ellison
 */
@Getter
@Setter
public class MultipleChoiceQuestion extends Question {
   //the list of possible answers for this question.
   private ArrayList<String> answers;

   @Override
   public void grade(QuestionResponse response) {
   }

   @Override
   public QuestionTypeName getTypeName() {
      return QuestionTypeName.MULTIPLE_CHOICE;
   }
}
