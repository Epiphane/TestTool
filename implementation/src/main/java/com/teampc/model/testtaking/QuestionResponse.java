package com.teampc.model.testtaking;

import com.teampc.model.question.Question;
import lombok.Getter;
import lombok.Setter;

/**
 * The response to a Test Question
 * @author Thomas Steinke
 *
 */
public abstract class QuestionResponse {

   @Getter
   @Setter
   private Question question;

   /**
    * Returns whether or not the question has been answered
    */
   public abstract boolean isComplete();

   @Getter
   private int pointsReceived;

}
