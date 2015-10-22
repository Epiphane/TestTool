package com.teampc.testtaking;

import java.util.Map;

import lombok.Getter;
import lombok.Setter;

/**
 * An answer to a matching question.
 * @author Zach Arend
 */
public abstract class MatchingQuestionResponse extends QuestionResponse {
   private Map<String, String> pairings;
}
