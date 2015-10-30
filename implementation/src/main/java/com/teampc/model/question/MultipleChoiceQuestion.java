package com.teampc.model.question;

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
public abstract class MultipleChoiceQuestion extends Question {
   //the list of possible answers for this question.
   private ArrayList<String> answers;
}
