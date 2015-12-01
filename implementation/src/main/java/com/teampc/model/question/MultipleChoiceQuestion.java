package com.teampc.model.question;

import com.teampc.model.testtaking.MultipleChoiceQuestionResponse;
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
public class MultipleChoiceQuestion extends Question/*<MultipleChoiceQuestionResponse>*/ {
   //the list of possible answers for this question.
   private ArrayList<String> answers = new ArrayList<String>();

   /** {@inheritDoc} */
   @Override
   public QuestionType getType() {
      return QuestionType.MULTIPLE_CHOICE;
   }
}
