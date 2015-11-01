package com.teampc.model.question;

/**
 * A representation of an answer to a question
 * @author Andy DuFrene
 *
 */
public interface Answer {
    /**
     * Tells whether this is the correct answer for the given question
     */
    boolean isCorrect(Question question);
}
