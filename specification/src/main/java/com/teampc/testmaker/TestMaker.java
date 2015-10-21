package com.teampc.testmaker;

import com.teampc.questions.Question;
import com.teampc.test.Test;

import java.util.ArrayList;

/**
 * A class to auto-generate a test.
 */
public abstract class TestMaker {

    /**
     * The number of questions to be on the test
     */
    int numQuestions;

    /**
     * A list of potential test questions
     */
    ArrayList<Question> potentialQuestions;

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
