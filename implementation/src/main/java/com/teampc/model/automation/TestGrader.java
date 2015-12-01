package com.teampc.model.automation;

import com.teampc.model.test.Test;

import java.util.ArrayList;

/**
 * Class to auto-grade a test.
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


}
