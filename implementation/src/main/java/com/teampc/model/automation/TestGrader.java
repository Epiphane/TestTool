package com.teampc.model.automation;

import com.teampc.model.test.Test;

import java.util.ArrayList;

import com.teampc.model.testtaking.*;

/**
 * TestGrader class to auto-grade a test.
 *
 * @author Zach Arend (zarend@calpoly.edu)
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

    /**
     * Grade a submission, given a key.
     * @param s The submission that will be graded
     * @param key The key for the test that the submission will be graded against.
     pre:
        s != null && key != null
     post:

     */
    public static void gradeTest(Submission s, Key key) {
        for (int i = 0; i < s.responses.size(); i++) {
            key.responses.get(i).assignPoints(s.responses.get(i), 1);
        }

       s.grade = 0;
       for (int  i = 0; i < s.responses.size(); i++) {
          s.grade += s.responses.get(i).getPointsReceived();
       }
    }
}
