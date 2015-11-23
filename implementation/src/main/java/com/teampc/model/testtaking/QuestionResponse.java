package com.teampc.model.testtaking;

import com.teampc.model.question.Question;
import lombok.Getter;
import lombok.Setter;

/**
 * The response to a Test Question
 * @author Thomas Steinke
 *
 */
public abstract class QuestionResponse<T extends QuestionResponse> {

   @Getter
   @Setter
   private Question question;

   @Getter
   protected int pointsReceived;

   /**
    * Returns whether or not the questions been answered
    */
   public abstract boolean isComplete();


   /**
    *
          pre: questionResponse != null && questionResponse.isComplete()
    *
    *
          post: questionResponse.pointsReceived >= 0
    *
    *
    * Using self as the model for a correct answer, grade the argument response and assign points
    * to questionResponse.pointsReceived based on how correct its answer is.
    * @param questionResponse response to grade
    */
   public abstract void assignPoints(T questionResponse);

}
