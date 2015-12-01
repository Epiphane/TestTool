package com.teampc.model.testtaking;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;

/**
 * A response to a code question.
 * @author Zach Arend
 */
@AllArgsConstructor
@Slf4j
public class CodeQuestionResponse extends QuestionResponse<CodeQuestionResponse> {
   /**
    * A plaintext version of the student's answer, or the plaintext grading script
    */
   private String codeAnswer;

   /** {@inheritDoc} */
   @Override
   public boolean isComplete() {
      return true;
   }

   /** {@inheritDoc} */
   @Override
   public void assignPoints(CodeQuestionResponse questionResponse, int maxPoints) {
      int score = 0;
      try {
         Process process = Runtime.getRuntime().exec(new String[] { codeAnswer, questionResponse.codeAnswer});
         score = process.waitFor();
      } catch (InterruptedException | IOException e) {
         log.error("Error grading code question with script: " + codeAnswer, e);
      }
      questionResponse.pointsReceived = score;
   }

   @Override
   public String toString() {
      return codeAnswer;
   }
}
