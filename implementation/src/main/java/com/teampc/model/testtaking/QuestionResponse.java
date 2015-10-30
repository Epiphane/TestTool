package com.teampc.model.testtaking;

import com.teampc.model.admin.*;
import com.teampc.model.question.*;
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
