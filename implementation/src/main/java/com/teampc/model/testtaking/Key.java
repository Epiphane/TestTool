package com.teampc.model.testtaking;

import com.teampc.model.admin.*;
import com.teampc.model.question.*;
import java.util.*;

/**
 * A Key containing the correct responses
 * @author Thomas Steinke
 *
 */
public abstract class Key extends Submission {
   /**
    * Return whether the key is complete or not
    */
   public abstract boolean isComplete();

   /**
    * Grade the test against a teacher's key
    */
   public abstract void gradeTest(Submission submission);
}
