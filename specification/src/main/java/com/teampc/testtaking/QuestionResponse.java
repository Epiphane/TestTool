package com.teampc.testtaking;

import com.teampc.admin.*;
import com.teampc.questions.*;
import java.util.*;

/**
 * 
 */
public abstract class QuestionResponse {

   public Question question;

   /**
    * Returns whether or not the question has been answered
    */
   public abstract void isComplete();

   /**
    * Checks whether the question response is correct
    */
   public abstract boolean isCorrect(QuestionResponse correctAnswer);

}
