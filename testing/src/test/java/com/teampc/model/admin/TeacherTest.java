package com.teampc.model.admin;

import com.teampc.model.admin.Teacher;
import testing.CombinationSupport;

import org.junit.runner.RunWith;
import testing.runner.SpestRunner;
import org.junit.Before;
import org.junit.Test;
import org.junit.Assert;

import testing.JavaTestUtility;
import format.ClassNameFormat;
import com.teampc.model.admin.Teacher;

import java.io.File;
import com.rits.cloning.Cloner;

import java.util.*;

import static testing.JavaTestUtility.getFieldValue;

@RunWith(SpestRunner.class)
public class TeacherTest
{
    @Before
    public void setUp()
    {
        testObj = (com.teampc.model.admin.Teacher)javaTestUtility.getSampleObject(clazz);

    }

    /*Start generated tests*/
    private Class clazz = com.teampc.model.admin.Teacher.class;

    private Cloner cloner = new Cloner();
    private File rootDirectory = new File("/home/andy/dev/school/TestTool/implementation");
    private File sourceFile = new File("/home/andy/dev/school/TestTool/implementation/src/main/java/com/teampc/model/admin/Teacher.java");
    private JavaTestUtility javaTestUtility = new JavaTestUtility(rootDirectory, sourceFile, false);
    private com.teampc.model.admin.Teacher testObj;
    @Test
    public void addCourseTest_0() throws Exception
    {
        int testComboIndex;

        String methodId = "addCourse_com.teampc.model.admin.course.Course";
        List<com.teampc.model.admin.course.Course> testPoints_0 = javaTestUtility.getSampleObjects(testObj, methodId, "course", com.teampc.model.admin.course.Course.class);
        int[][] combinations = CombinationSupport.getCombinations(testPoints_0.size());

        com.teampc.model.admin.course.Course param_0;
        for(testComboIndex = 0; testComboIndex < combinations.length; testComboIndex++)
        {
            param_0 = testPoints_0.get(combinations[testComboIndex][0]);

            testObj.addCourse(param_0);
            Assert.assertTrue(testObj.getCourses() != null);
            Assert.assertTrue(testObj.getCourses().size() > 0);
            Assert.assertTrue(((boolean) javaTestUtility.getMethodValue(testObj, "isAssignedToAllCourses", )));
            setUp();
        }
    }

    @Test
    public void removeCourseTest_1() throws Exception
    {
        int testComboIndex;

        String methodId = "removeCourse_com.teampc.model.admin.course.Course";
        List<com.teampc.model.admin.course.Course> testPoints_0 = javaTestUtility.getSampleObjects(testObj, methodId, "course", com.teampc.model.admin.course.Course.class);
        int[][] combinations = CombinationSupport.getCombinations(testPoints_0.size());

        com.teampc.model.admin.course.Course param_0;
        for(testComboIndex = 0; testComboIndex < combinations.length; testComboIndex++)
        {
            param_0 = testPoints_0.get(combinations[testComboIndex][0]);

            testObj.removeCourse(param_0);
            Assert.assertTrue(testObj.getCourses() != null);
            Assert.assertTrue(((boolean) javaTestUtility.getMethodValue(testObj, "isAssignedToAllCourses", )));
            setUp();
        }
    }

    @Test
    public void teachesCourseTest_2() throws Exception
    {
        int testComboIndex;

        String methodId = "teachesCourse_com.teampc.model.admin.course.Course";
        List<com.teampc.model.admin.course.Course> testPoints_0 = javaTestUtility.getSampleObjects(testObj, methodId, "course", com.teampc.model.admin.course.Course.class);
        int[][] combinations = CombinationSupport.getCombinations(testPoints_0.size());

        com.teampc.model.admin.course.Course param_0;
        for(testComboIndex = 0; testComboIndex < combinations.length; testComboIndex++)
        {
            param_0 = testPoints_0.get(combinations[testComboIndex][0]);

            testObj.teachesCourse(param_0);
            Assert.assertTrue(testObj.getCourses() != null);
            Assert.assertTrue(((boolean) javaTestUtility.getMethodValue(testObj, "isAssignedToAllCourses", )));
            setUp();
        }
    }
    /*End generated tests*/
}
