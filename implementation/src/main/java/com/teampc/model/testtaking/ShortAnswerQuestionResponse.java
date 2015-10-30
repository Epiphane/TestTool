package com.teampc.model.testtaking;

import lombok.Getter;
import lombok.Setter;

/**
 * A response to a short answer question.
 * @author Zach Arend
 */
@Getter
@Setter
public abstract class ShortAnswerQuestionResponse {
    private String answer;
}
