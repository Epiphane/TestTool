package com.teampc.model.admin;

import com.teampc.model.admin.Student;
import testing.CombinationSupport;

import org.junit.runner.RunWith;
import testing.runner.SpestRunner;
import org.junit.Before;
import org.junit.Test;
import org.junit.Assert;

import testing.JavaTestUtility;
import format.ClassNameFormat;
import com.teampc.model.admin.Student;

import java.io.File;
import com.rits.cloning.Cloner;

import java.util.*;

import static testing.JavaTestUtility.getFieldValue;

@RunWith(SpestRunner.class)
public class StudentTest
{
    @Before
    public void setUp()
    {
        testObj = (com.teampc.model.admin.Student)javaTestUtility.getSampleObject(clazz);

    }

    /*Start generated tests*/
    private Class clazz = com.teampc.model.admin.Student.class;

    private Cloner cloner = new Cloner();
    private File rootDirectory = new File("/home/andy/dev/school/TestTool/implementation");
    private File sourceFile = new File("/home/andy/dev/school/TestTool/implementation/src/main/java/com/teampc/model/admin/Student.java");
    private JavaTestUtility javaTestUtility = new JavaTestUtility(rootDirectory, sourceFile, false);
    private com.teampc.model.admin.Student testObj;
    @Test
    public void addCourseTest_0() throws Exception
    {
        int testComboIndex;

        String methodId = "addCourse_com.teampc.model.admin.course.Course";
        List<com.teampc.model.admin.course.Course> testPoints_0 = javaTestUtility.getSampleObjects(testObj, methodId, "course", com.teampc.model.admin.course.Course.class);
        int[][] combinations = CombinationSupport.getCombinations(testPoints_0.size());

        Class[] parameterClasses = {com.teampc.model.admin.course.Course.class};
        List<com.teampc.model.admin.course.Course> cothers_0 = javaTestUtility.getUniversalValues(testObj, methodId, 0);
        boolean exists_0 = false;
        List<com.teampc.model.admin.course.Course> cothers_1 = javaTestUtility.getUniversalValues(testObj, methodId, 1);
        boolean exists_1 = false;
        com.teampc.model.admin.course.Course param_0;
        for(testComboIndex = 0; testComboIndex < combinations.length; testComboIndex++)
        {
            param_0 = testPoints_0.get(combinations[testComboIndex][0]);

            testObj.addCourse(param_0);
            for(com.teampc.model.admin.course.Course cother : cothers_0)
            {
            }
            for(com.teampc.model.admin.course.Course cother : cothers_1)
            {
                exists_1 = exists_1 || (cother.equals(param_0));
            }
            Assert.assertTrue(exists_1);

            setUp();
        }
    }

    @Test
    public void removeCourseTest_1() throws Exception
    {
        java.util.List<com.teampc.model.admin.course.Enrolled> enrolledCourses = cloner.deepClone(getFieldValue(testObj, "enrolledCourses", java.util.List.class));

        int testComboIndex;

        String methodId = "removeCourse_com.teampc.model.admin.course.Course";
        List<com.teampc.model.admin.course.Course> testPoints_0 = javaTestUtility.getSampleObjects(testObj, methodId, "course", com.teampc.model.admin.course.Course.class);
        int[][] combinations = CombinationSupport.getCombinations(testPoints_0.size());

        Class[] parameterClasses = {com.teampc.model.admin.course.Course.class};
        List<com.teampc.model.admin.course.Course> cothers_0 = javaTestUtility.getUniversalValues(testObj, methodId, 0);
        boolean exists_3 = false;
        List<com.teampc.model.admin.course.Course> cothers_1 = javaTestUtility.getUniversalValues(testObj, methodId, 1);
        boolean exists_4 = false;
        com.teampc.model.admin.course.Course param_0;
        for(testComboIndex = 0; testComboIndex < combinations.length; testComboIndex++)
        {
            param_0 = testPoints_0.get(combinations[testComboIndex][0]);

            testObj.removeCourse(param_0);
            for(com.teampc.model.admin.course.Course cother : cothers_0)
            {
                exists_3 = exists_3 || (cother.equals(param_0));
            }
            Assert.assertTrue(exists_3);
            for(com.teampc.model.admin.course.Course cother : cothers_1)
            {
            }
            Assert.assertTrue(!(exists_4));

            setUp();
        }
    }
    /*End generated tests*/
}
