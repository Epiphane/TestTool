package com.teampc.model.testtaking;

import com.teampc.model.question.Question;
import lombok.Getter;
import lombok.Setter;

/**
 * The response to a Test Question
 * @author adufrene
 *
 */
public abstract class QuestionResponse<T extends QuestionResponse> {

   @Getter
   @Setter
   private Question question;

   @Getter
   @Setter
   protected int pointsReceived;

   @Getter
   @Setter
   private String comment = "";

   /**
    * Returns whether or not the questions been answered
    */
   public abstract boolean isComplete();


   /**
    *
    *
    * Using self as the model for a correct answer, grade the argument response and assign points
    * to questionResponse.pointsReceived based on how correct its answer is.
    * @param questionResponse response to grade
    *
          pre: questionResponse != null && questionResponse.isComplete()
    *
    *
          post: questionResponse.pointsReceived >= 0
    * @param maxPoints
    *
    */
   public abstract void assignPoints(T questionResponse, int maxPoints);

}
