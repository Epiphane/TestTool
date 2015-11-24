package com.teampc.model.test;

import com.teampc.model.test.Test;
import testing.CombinationSupport;

import org.junit.runner.RunWith;
import testing.runner.SpestRunner;
import org.junit.Before;
import org.junit.Test;
import org.junit.Assert;

import testing.JavaTestUtility;
import format.ClassNameFormat;
import com.teampc.model.test.Test;

import java.io.File;
import com.rits.cloning.Cloner;

import java.util.*;

import static testing.JavaTestUtility.getFieldValue;

@RunWith(SpestRunner.class)
public class TestTest
{
    @Before
    public void setUp()
    {
        testObj = (com.teampc.model.test.Test)javaTestUtility.getSampleObject(clazz);

    }

    /*Start generated tests*/
    private Class clazz = com.teampc.model.test.Test.class;

    private Cloner cloner = new Cloner();
    private File rootDirectory = new File("/home/andy/dev/school/TestTool/implementation");
    private File sourceFile = new File("/home/andy/dev/school/TestTool/implementation/src/main/java/com/teampc/model/test/Test.java");
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
    public void moveQuestionTest_1() throws Exception
    {
        int testComboIndex;

        String methodId = "moveQuestion_int_int";
        List<java.lang.Integer> testPoints_0 = javaTestUtility.getSamplePrimitives(testObj, methodId, "from", java.lang.Integer.class);
        List<java.lang.Integer> testPoints_1 = javaTestUtility.getSamplePrimitives(testObj, methodId, "to", java.lang.Integer.class);
        int[][] combinations = CombinationSupport.getCombinations(testPoints_0.size(), testPoints_1.size());

        int param_0;
        int param_1;
        for(testComboIndex = 0; testComboIndex < combinations.length; testComboIndex++)
        {
            param_0 = testPoints_0.get(combinations[testComboIndex][0]);
            param_1 = testPoints_1.get(combinations[testComboIndex][1]);

            testObj.moveQuestion(param_0, param_1);
            setUp();
        }
    }

    @Test
    public void publishTest_2() throws Exception
    {
        boolean published = getFieldValue(testObj, "published", java.lang.Boolean.class);


        String methodId = "publish";

        testObj.publish();
        Assert.assertTrue(published);
        setUp();
    }
    /*End generated tests*/
}