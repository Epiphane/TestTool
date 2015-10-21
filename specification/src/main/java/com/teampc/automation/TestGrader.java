package com.teampc.automation;

import com.teampc.test.Test;

import java.util.ArrayList;

/**
 * Class to auto-grade a test.
 *
 * Created by Greg
 */
public abstract class TestGrader {

    /**
     * The tests to grade.
     */
    public ArrayList<Test> tests;

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


}
