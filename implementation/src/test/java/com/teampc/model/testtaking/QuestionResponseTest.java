package com.teampc.model.testtaking;

import com.teampc.model.testtaking.QuestionResponse;
import testing.CombinationSupport;

import org.junit.runner.RunWith;
import testing.runner.SpestRunner;
import org.junit.Before;
import org.junit.Test;
import org.junit.Assert;

import testing.JavaTestUtility;
import format.ClassNameFormat;
import com.teampc.model.testtaking.QuestionResponse;

import java.io.File;
import com.rits.cloning.Cloner;

import java.util.*;

import static testing.JavaTestUtility.getFieldValue;

@RunWith(SpestRunner.class)
public class QuestionResponseTest
{
    @Before
    public void setUp()
    {
        testObj = (com.teampc.model.testtaking.QuestionResponse)javaTestUtility.getSampleObject(clazz);

    }

    /*Start generated tests*/
    private Class clazz = com.teampc.model.testtaking.QuestionResponse.class;

    private Cloner cloner = new Cloner();
    private File rootDirectory = new File("/home/andy/dev/school/TestTool/implementation");
    private File sourceFile = new File("/home/andy/dev/school/TestTool/implementation/src/main/java/com/teampc/model/testtaking/QuestionResponse.java");
    private JavaTestUtility javaTestUtility = new JavaTestUtility(rootDirectory, sourceFile, false);
    private com.teampc.model.testtaking.QuestionResponse testObj;
    @Test
    public void assignPointsTest_0() throws Exception
    {
        int testComboIndex;

        String methodId = "assignPoints_com.teampc.model.testtaking.QuestionResponse";
        List<com.teampc.model.testtaking.QuestionResponse> testPoints_0 = javaTestUtility.getSampleObjects(testObj, methodId, "questionResponse", com.teampc.model.testtaking.QuestionResponse.class);
        int[][] combinations = CombinationSupport.getCombinations(testPoints_0.size());

        com.teampc.model.testtaking.QuestionResponse param_0;
        for(testComboIndex = 0; testComboIndex < combinations.length; testComboIndex++)
        {
            param_0 = testPoints_0.get(combinations[testComboIndex][0]);

            testObj.assignPoints(param_0);
            Assert.assertTrue(javaTestUtility.getFieldValue(param_0, "pointsReceived", int.class) >= 0);
            setUp();
        }
    }
    /*End generated tests*/
}
