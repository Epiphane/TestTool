package com.teampc.test;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author Andy DuFrene
 */
@Data
@EqualsAndHashCode(callSuper=true)
public abstract class InClassTest extends Test {

    private long duration;

}
