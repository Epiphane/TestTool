package com.teampc.model.testtaking;

import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import lombok.Getter;
import lombok.Setter;

import java.io.IOException;

/**
 * A response to a code question.
 * @author Zach Arend
 */
@NoArgsConstructor
@AllArgsConstructor
@Slf4j
public class CodeQuestionResponse extends QuestionResponse<CodeQuestionResponse> {
   //A plaintext version of the student'sn answer, or the path to the grading script.
   @Getter
   @Setter
   private String answer;

   /** {@inheritDoc} */
   @Override
   public boolean isComplete() {
      return true;
   }

   /** {@inheritDoc} */
   @Override
   public void assignPoints(CodeQuestionResponse questionResponse) {
      int score = 0;
      try {
         Process process = Runtime.getRuntime().exec(new String[] { answer, questionResponse.answer});
         score = process.waitFor();
      } catch (InterruptedException | IOException e) {
         log.error("Error grading code question with script: " + answer, e);
      }
      questionResponse.pointsReceived = score;
   }

   @Override
   public String toString() {
      return answer;
   }
}
