package com.teampc.model.test;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * InClassTest represents a test to be taken during a class session
 * @author Andy DuFrene
 *
 */
@Data
@EqualsAndHashCode(callSuper=true)
public abstract class InClassTest extends Test {

    /** Duration allowed for test taking, in milliseconds */
    private long duration;

    /**
     * Check if this test is a practice test; Should it count towards a
     * grading statistic?
     * */
    private boolean practice;

}
