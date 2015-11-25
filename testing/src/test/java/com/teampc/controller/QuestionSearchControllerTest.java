package com.teampc.controller;

import com.teampc.controller.QuestionSearchController;
import testing.CombinationSupport;

import org.junit.runner.RunWith;
import testing.runner.SpestRunner;
import org.junit.Before;
import org.junit.Test;
import org.junit.Assert;

import testing.JavaTestUtility;
import format.ClassNameFormat;
import com.teampc.controller.QuestionSearchController;

import java.io.File;
import com.rits.cloning.Cloner;

import java.util.*;

import static testing.JavaTestUtility.getFieldValue;

@RunWith(SpestRunner.class)
public class QuestionSearchControllerTest
{
    @Before
    public void setUp()
    {
        testObj = (com.teampc.controller.QuestionSearchController)javaTestUtility.getSampleObject(clazz);

    }

    /*Start generated tests*/
    private Class clazz = com.teampc.controller.QuestionSearchController.class;

    private Cloner cloner = new Cloner();
    private File rootDirectory = new File("/home/andy/dev/school/TestTool/implementation");
    private File sourceFile = new File("/home/andy/dev/school/TestTool/implementation/src/main/java/com/teampc/controller/QuestionSearchController.java");
    private JavaTestUtility javaTestUtility = new JavaTestUtility(rootDirectory, sourceFile, false);
    private com.teampc.controller.QuestionSearchController testObj;
    @Test
    public void initializeTest_0() throws Exception
    {
        javafx.scene.control.ListView<com.teampc.model.admin.course.Course> courseListView = cloner.deepClone(getFieldValue(testObj, "courseListView", javafx.scene.control.ListView.class));
        javafx.scene.control.ListView<com.teampc.model.question.Question> questionListView = cloner.deepClone(getFieldValue(testObj, "questionListView", javafx.scene.control.ListView.class));
        javafx.scene.control.ListView<com.teampc.model.test.Test> testListView = cloner.deepClone(getFieldValue(testObj, "testListView", javafx.scene.control.ListView.class));

        int testComboIndex;

        String methodId = "initialize_java.net.URL_java.util.ResourceBundle";
        List<java.net.URL> testPoints_0 = javaTestUtility.getSampleObjects(testObj, methodId, "fxmlFileLocation", java.net.URL.class);
        List<java.util.ResourceBundle> testPoints_1 = javaTestUtility.getSampleObjects(testObj, methodId, "resources", java.util.ResourceBundle.class);
        int[][] combinations = CombinationSupport.getCombinations(testPoints_0.size(), testPoints_1.size());

        java.net.URL param_0;
        java.util.ResourceBundle param_1;
        for(testComboIndex = 0; testComboIndex < combinations.length; testComboIndex++)
        {
            param_0 = testPoints_0.get(combinations[testComboIndex][0]);
            param_1 = testPoints_1.get(combinations[testComboIndex][1]);

            testObj.initialize(param_0, param_1);
            Assert.assertTrue(courseListView != null);
            Assert.assertTrue(testListView != null);
            Assert.assertTrue(questionListView != null);
            setUp();
        }
    }
    /*End generated tests*/
}