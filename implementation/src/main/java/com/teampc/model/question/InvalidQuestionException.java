package com.teampc.model.question;

/**
 * Created by adufrene on 11/30/15.
 *
 * An exception indicating something when wrong when creating/editing a question
 */
public class InvalidQuestionException extends Exception {

   public InvalidQuestionException(String message) {
      super(message);
   }
}
