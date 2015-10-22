package com.teampc.test;

import java.util.List;

/**
 * @author Andy DuFrene
 *
 * A Class to represent a practice test 
 */
public abstract class PracticeTest extends TakeHomeTest {

    /**
     * Allows user to retake test. Abandons current attempt if not finished.
     */
    public abstract void retake(boolean shuffleQuestions);
}
