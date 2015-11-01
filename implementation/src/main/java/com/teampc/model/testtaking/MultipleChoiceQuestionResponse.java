package com.teampc.model.testtaking;

import lombok.Getter;
import lombok.Setter;

/**
 * A response to a multiple choice question question.
 * @author Zach Arend
 */
@Getter
@Setter
public abstract class MultipleChoiceQuestionResponse extends QuestionResponse {
    // an int representing which answer was chosen. 1 for A, 2 for B, etc
    private int answer;
}
