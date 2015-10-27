package com.teampc.test;

/**
 * A Test that can be taken home and completed on the student's own time
 * @author Andy DuFrene
 *
 */
public abstract class TakeHomeTest extends Test {
    
    /**
     * Returns the number of allowed retries remaining, a return value of
     * -1 indicates unlimited retries.
     */
    public abstract int getRemainingRetries();
}
