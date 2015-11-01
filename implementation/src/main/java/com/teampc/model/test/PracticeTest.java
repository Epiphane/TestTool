package com.teampc.model.test;

import com.teampc.model.question.Answer;

import java.util.List;

/**
 * A practice test that won't count towards an official grade
 * @author Andy DuFrene
 *
 */
public abstract class PracticeTest extends TakeHomeTest {
    /**
     * Returns list of correct answers to this test
     */
    public abstract List<Answer> getCorrectAnswers();
}
