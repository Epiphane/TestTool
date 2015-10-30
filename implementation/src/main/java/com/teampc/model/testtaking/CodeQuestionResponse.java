package com.teampc.model.testtaking;

import lombok.Getter;
import lombok.Setter;

/**
 * A response to a code question.
 * @author Zach Arend
 */
@Getter
@Setter
public abstract class CodeQuestionResponse extends QuestionResponse {
   //A plaintext version of the student's answer.
   private String codeAnswer;
}
