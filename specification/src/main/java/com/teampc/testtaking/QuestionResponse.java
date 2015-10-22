package com.teampc.testtaking;

import com.teampc.admin.*;
import com.teampc.question.*;
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

}
