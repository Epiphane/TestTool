package com.teampc.model.test;

import com.teampc.model.test.InClassTest;
import testing.CombinationSupport;

import org.junit.runner.RunWith;
import testing.runner.SpestRunner;
import org.junit.Before;
import org.junit.Test;
import org.junit.Assert;

import testing.JavaTestUtility;
import format.ClassNameFormat;
import com.teampc.model.test.InClassTest;

import java.io.File;
import com.rits.cloning.Cloner;

import java.util.*;

import static testing.JavaTestUtility.getFieldValue;

@RunWith(SpestRunner.class)
public class InClassTestTest
{
    @Before
    public void setUp()
    {
        testObj = (com.teampc.model.test.InClassTest)javaTestUtility.getSampleObject(clazz);

    }

    /*Start generated tests*/
    private Class clazz = com.teampc.model.test.InClassTest.class;

    private Cloner cloner = new Cloner();
    private File rootDirectory = new File("/home/andy/dev/school/TestTool/implementation");
    private File sourceFile = new File("/home/andy/dev/school/TestTool/implementation/src/main/java/com/teampc/model/test/InClassTest.java");
    private JavaTestUtility javaTestUtility = new JavaTestUtility(rootDirectory, sourceFile, false);
    private com.teampc.model.test.InClassTest testObj;    /*End generated tests*/
}
