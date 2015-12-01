package com.teampc.model.automation;

import com.teampc.model.question.Question;
import com.teampc.model.test.Test;
import com.teampc.model.test.TestDifficulty;

import java.util.ArrayList;

/**
 * A class to auto-generate a test.
 *
 * @author Greg Sawers (gsawers@calpoly.edu)
 */
public abstract class TestMaker {

    /**
     * The number of questions to be on the test
     * @Data
     */
    private int numQuestions;

    /**
     * A list of potential test questions
     * @Data
     */
    private ArrayList<Question> potentialQuestions;

    /**
     * How hard the test should be
     * @Data
     */
    private TestDifficulty difficulty;

    /**
     * Generic constructor
     */
    public TestMaker(){

    }

    /**
     * Given the instance variables, create a new test
     * @return The newly generated test.
     */
    public abstract Test makeTest();
}
