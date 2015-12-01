package com.teampc.model.automation;

import com.teampc.model.test.Test;

import java.util.ArrayList;

import com.teampc.model.testtaking.*;

/**
 * Class to auto-grade a test.
 *
 * Created by Greg
 */
public abstract class TestGrader {

    /**
     * The tests to grade.
     * @Data
     */
    private ArrayList<Test> tests;

    /**
     * Constructor to make an instance of a TestGrader with a given test
     * @param test The test to be graded.
     */
    public TestGrader(ArrayList<Test> test) {
        this.tests = test;
    }

    /**
     * Grade the given test for each student who has taken it.
     */
    public abstract void gradeTests();

    public static void gradeTest(Submission s, Key key) {
        for (int i = 0; i < s.responses.size(); i++) {
            s.responses.get(i).assignPoints(key.responses.get(i));
        }
    }
}
