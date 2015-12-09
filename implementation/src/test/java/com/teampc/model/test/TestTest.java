package com.teampc.model.test;

import com.rits.cloning.Cloner;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import testing.CombinationSupport;
import testing.JavaTestUtility;
import testing.runner.SpestRunner;

import java.io.File;
import java.util.Date;
import java.util.List;

import static testing.JavaTestUtility.getFieldValue;

@RunWith(SpestRunner.class)
public class TestTest
{
    @Before
    public void setUp()
    {
        testObj = new com.teampc.model.test.Test("Test", new Date(System.currentTimeMillis() - 1000 * 60 * 60 * 24), new Date(), "Spest");

    }

    /*Start generated tests*/
    private Class clazz = com.teampc.model.test.Test.class;

    private Cloner cloner = new Cloner();
    private File rootDirectory = new File(".");
    private File sourceFile = new File("src/main/java/com/teampc/model/test/Test.java");
    private JavaTestUtility javaTestUtility = new JavaTestUtility(rootDirectory, sourceFile, false);
    private com.teampc.model.test.Test testObj;
    @Test
    public void toStringTest_0() throws Exception
    {

        String methodId = "toString";

        testObj.toString();
        setUp();
    }

    @Test
    public void publishTest_2() throws Exception
    {
        testObj.publish();
        Assert.assertTrue(testObj.isPublished());
        setUp();
    }
    /*End generated tests*/
}
