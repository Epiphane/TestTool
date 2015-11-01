package com.teampc.model.admin.access;

import com.teampc.model.admin.User;
import com.teampc.model.test.Test;

/**
 * Identifies the current UserSession as someone taking a test.
 * This sort of session limits access of the user to the rest of the program.
 *
 * Created by Greg.
 */
public abstract class TestTakingSession extends UserSession{

    /**
     * The Test the user is taking and allowed to see.
     * @Data
     */
    private Test test;
    /**
     * The time limit for the test in millis;
     * @Data
     */
    private long timeLimit;

    private TestTakingSession(User user, long limit){super(user);}

    /**
     * Get what type of test is being taken (In Class, Take Home, Practice).
     * @return The class of the test being taken.
     */
    public abstract Class getTestType();

    /**
     * Give the user access to the test.
     */
    public abstract void startTest();

    /**
     * Revoke access to change answers on test.
     */
    public abstract void endTest();

    /**
     * Submit the test answers.
     */
    public abstract void submitTest();
}
