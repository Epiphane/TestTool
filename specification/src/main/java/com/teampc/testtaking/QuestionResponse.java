package com.teampc.testtaking;

import com.teampc.admin.*;
import com.teampc.question.*;
import java.util.*;

/**
 * The response to a Test Question
 * @author Thomas Steinke
 *
 */
public abstract class QuestionResponse {

   public Question question;

   /**
    * Returns whether or not the question has been answered
    */
   public abstract void isComplete();

   private int pointsReceived;

}
