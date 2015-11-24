package com.teampc.model.question;

import com.teampc.model.question.Question;
import testing.CombinationSupport;

import org.junit.runner.RunWith;
import testing.runner.SpestRunner;
import org.junit.Before;
import org.junit.Test;
import org.junit.Assert;

import testing.JavaTestUtility;
import format.ClassNameFormat;
import com.teampc.model.question.Question;

import java.io.File;
import com.rits.cloning.Cloner;

import java.util.*;

import static testing.JavaTestUtility.getFieldValue;

@RunWith(SpestRunner.class)
public class QuestionTest
{
    @Before
    public void setUp()
    {
        testObj = (com.teampc.model.question.Question)javaTestUtility.getSampleObject(clazz);

    }

    /*Start generated tests*/
    private Class clazz = com.teampc.model.question.Question.class;

    private Cloner cloner = new Cloner();
    private File rootDirectory = new File("/Users/james/TestTool/implementation");
    private File sourceFile = new File("/Users/james/TestTool/implementation/src/main/java/com/teampc/model/question/Question.java");
    private JavaTestUtility javaTestUtility = new JavaTestUtility(rootDirectory, sourceFile, false);
    private com.teampc.model.question.Question testObj;
    @Test
    public void gradeTest_0() throws Exception
    {
        int testComboIndex;

        String methodId = "grade_com.teampc.model.testtaking.QuestionResponse";
        List<com.teampc.model.testtaking.QuestionResponse> testPoints_0 = javaTestUtility.getSampleObjects(testObj, methodId, "response", com.teampc.model.testtaking.QuestionResponse.class);
        int[][] combinations = CombinationSupport.getCombinations(testPoints_0.size());

        com.teampc.model.testtaking.QuestionResponse param_0;
        for(testComboIndex = 0; testComboIndex < combinations.length; testComboIndex++)
        {
            param_0 = testPoints_0.get(combinations[testComboIndex][0]);

            testObj.grade(param_0);
            Assert.assertTrue(param_0.getPoints() != null);
            setUp();
        }
    }
    /*End generated tests*/
}