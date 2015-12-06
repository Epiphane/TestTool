package com.teampc.controller.question;

import com.teampc.model.question.InvalidQuestionException;
import com.teampc.model.question.Question;
import com.teampc.model.testtaking.QuestionResponse;

import java.util.Optional;

/**
 * Created by adufrene on 11/30/15.
 *
 * An interface for dealing with questions of a certain type
 */
public interface QuestionTypeController<T extends QuestionResponse> {
   /**
    * Create a question given a prompt and using supplementary information from the controller.
    *
    * pre: prompt != null && questionId != null && questionId.map(id -> id > 0).orElse(true)
    *
    * post: return.getPrompt.equals(prompt) && questionId.map(id -> return.getId() == id).orElse(true)
    */
   Question<T> createQuestion(String prompt, Optional<Integer> questionId) throws InvalidQuestionException;

   /**
    * begin edit question flow
    */
   void setQuestion(T questionResponse);
}
