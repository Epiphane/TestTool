package com.teampc.automation;

import com.teampc.question.Question;
import com.teampc.test.Test;
import com.teampc.test.TestDifficulty;

import java.util.ArrayList;

/**
 * A class to auto-generate a test.
 *
 * Created by Greg.
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
