package com.teampc.dao;

import com.teampc.dao.AbstractDAO;
import testing.CombinationSupport;

import org.junit.runner.RunWith;
import testing.runner.SpestRunner;
import org.junit.Before;
import org.junit.Test;
import org.junit.Assert;

import testing.JavaTestUtility;
import format.ClassNameFormat;
import com.teampc.dao.AbstractDAO;

import java.io.File;
import com.rits.cloning.Cloner;

import java.util.*;

import static testing.JavaTestUtility.getFieldValue;

@RunWith(SpestRunner.class)
public class AbstractDAOTest
{
    @Before
    public void setUp()
    {
        testObj = (com.teampc.dao.AbstractDAO)javaTestUtility.getSampleObject(clazz);

    }

    /*Start generated tests*/
    private Class clazz = com.teampc.dao.AbstractDAO.class;

    private Cloner cloner = new Cloner();
    private File rootDirectory = new File("/home/andy/dev/school/TestTool/implementation");
    private File sourceFile = new File("/home/andy/dev/school/TestTool/implementation/src/main/java/com/teampc/dao/AbstractDAO.java");
    private JavaTestUtility javaTestUtility = new JavaTestUtility(rootDirectory, sourceFile, false);
    private com.teampc.dao.AbstractDAO testObj;
    @Test
    public void insertTest_0() throws Exception
    {
        int testComboIndex;

        String methodId = "insert_java.lang.Object";
        List<java.lang.Object> testPoints_0 = javaTestUtility.getSampleObjects(testObj, methodId, "item", java.lang.Object.class);
        int[][] combinations = CombinationSupport.getCombinations(testPoints_0.size());

        java.lang.Object param_0;
        for(testComboIndex = 0; testComboIndex < combinations.length; testComboIndex++)
        {
            param_0 = testPoints_0.get(combinations[testComboIndex][0]);

            testObj.insert(param_0);
            Assert.assertTrue(testObj.fetchAll().contains(param_0));
            setUp();
        }
    }

    @Test
    public void insertTest_1() throws Exception
    {
        int testComboIndex;

        String methodId = "insert_java.util.Collection";
        List<java.util.Collection> testPoints_0 = javaTestUtility.getSampleObjects(testObj, methodId, "items", java.util.Collection.class);
        int[][] combinations = CombinationSupport.getCombinations(testPoints_0.size());

        Class[] parameterClasses = {java.util.Collection.class};
        List<java.lang.Object> item_others_0 = javaTestUtility.getUniversalValues(testObj, methodId, 0);
        boolean forall_5 = true;
        java.util.Collection param_0;
        for(testComboIndex = 0; testComboIndex < combinations.length; testComboIndex++)
        {
            param_0 = testPoints_0.get(combinations[testComboIndex][0]);

            testObj.insert(param_0);
            for(java.lang.Object item_other : item_others_0)
            {
                forall_5 = forall_5 && (testObj.fetchAll().contains(item_other));
            }
            Assert.assertTrue(forall_5);

            setUp();
        }
    }
    /*End generated tests*/
}