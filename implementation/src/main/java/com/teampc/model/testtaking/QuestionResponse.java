package com.teampc.model.testtaking;

import com.teampc.dao.HasId;
import com.teampc.dao.definitions.question.QuestionDD;
import com.teampc.dao.definitions.response.QuestionResponseDD;
import com.teampc.model.question.Question;
import com.teampc.model.Model;
import lombok.Getter;
import lombok.Setter;

/**
 * The response to a Test Question
 * @author adufrene
 *
 */
public abstract class QuestionResponse<T extends QuestionResponse> implements Model<QuestionResponseDD> {
   @Getter
   @Setter
   protected Integer id;

   @Getter
   @Setter
   protected Question<?> question;

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
          post: questionResponse.pointsReceived >= 0 && questionResponse.pointsReceived <= maxPoints
    * @param maxPoints
    *
    */
   public abstract void assignPoints(T questionResponse, int maxPoints);

   /**
    * Copy self to return a new object
     */
   public abstract T copy();

   /**
    * copy parent fields from questionResponse
     */
   protected void finishCopy(QuestionResponse<T> questionResponse) {
      this.question = new Question<>(questionResponse.getQuestion());
      this.pointsReceived = questionResponse.pointsReceived;
      this.comment = questionResponse.comment;
   }

   public abstract QuestionResponseDD asEntity(QuestionDD q);

}
