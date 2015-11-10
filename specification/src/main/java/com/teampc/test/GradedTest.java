package com.teampc.test;

import com.google.common.collect.ImmutableList;

import lombok.Data;
import lombok.Builder;

import java.util.Optional;

import com.teampc.question.Question;

/**
 * A Class representing a test after it has been graded.
 * @author Andy DuFrene
 *
 */
@Data
// @Builder // uncomment when non-abstract
public abstract class GradedTest {

    /** Underlying test object that has been graded */
    private Test test;

    /** Questions within the test object that were graded as incorrect */
    private ImmutableList<Question> incorrectQuestions;

    /** Questions within the test object that were graded as correct */
    private ImmutableList<Question> correctQuestions;

    /** 
     * Attempt to retake the graded test. Returns an
     * <code>Optional.empty()</code> if the test can not be retaken, either due
     * to the test's type not allowing retakes, or the number of retakes has
     * been exceeded. 
     * */
    public abstract Optional<Test> retake();
}
