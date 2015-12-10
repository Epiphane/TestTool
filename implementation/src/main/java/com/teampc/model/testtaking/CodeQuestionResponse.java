package com.teampc.model.testtaking;

import lombok.*;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;

/**
 * A response to a code question.
 * @author Zach Arend
 */
@NoArgsConstructor
@AllArgsConstructor
@Data
@Slf4j
@EqualsAndHashCode(callSuper = true)
public class CodeQuestionResponse extends QuestionResponse<CodeQuestionResponse> {
   /**
    * A plaintext version of the student's answer, or the path to the grading program
    */
   private String codeAnswer;

   // HACK
   private String givenCode;

    /**
     * Copy constructor
     */
   public CodeQuestionResponse(CodeQuestionResponse response) {
      this.codeAnswer = response.codeAnswer;
      this.givenCode = response.givenCode;
      finishCopy(response);
   }

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
         // This is blatantly wrong, todo: // FIXME: 11/30/15
         Process process = Runtime.getRuntime().exec(new String[] { codeAnswer, questionResponse.codeAnswer});
         score = process.waitFor();
      } catch (InterruptedException | IOException e) {
         log.error("Error grading code question with script: " + codeAnswer, e);
      }
      questionResponse.pointsReceived = score;
   }

   /** {@inheritDoc} */
   @Override
   public CodeQuestionResponse copy() {
      return new CodeQuestionResponse(this);
   }

   @Override
   public String toString() {
      return codeAnswer;
   }
}
