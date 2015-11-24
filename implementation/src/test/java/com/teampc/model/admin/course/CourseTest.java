package com.teampc.model.admin.course;

import com.teampc.model.admin.course.Course;
import testing.CombinationSupport;

import org.junit.runner.RunWith;
import testing.runner.SpestRunner;
import org.junit.Before;
import org.junit.Test;
import org.junit.Assert;

import testing.JavaTestUtility;
import format.ClassNameFormat;
import com.teampc.model.admin.course.Course;

import java.io.File;
import com.rits.cloning.Cloner;

import java.util.*;

import static testing.JavaTestUtility.getFieldValue;

@RunWith(SpestRunner.class)
public class CourseTest
{
    @Before
    public void setUp()
    {
        testObj = (com.teampc.model.admin.course.Course)javaTestUtility.getSampleObject(clazz);

    }

    /*Start generated tests*/
    private Class clazz = com.teampc.model.admin.course.Course.class;

    private Cloner cloner = new Cloner();
    private File rootDirectory = new File("/Users/james/TestTool/implementation");
    private File sourceFile = new File("/Users/james/TestTool/implementation/src/main/java/com/teampc/model/admin/course/Course.java");
    private JavaTestUtility javaTestUtility = new JavaTestUtility(rootDirectory, sourceFile, false);
    private com.teampc.model.admin.course.Course testObj;
    @Test
    public void getEnrolledStudentsTest_0() throws Exception
    {

        java.util.List ret;
        String methodId = "getEnrolledStudents";
        Class[] parameterClasses = {};
        List<java.lang.Integer> is_0 = javaTestUtility.getUniversalValues(testObj, methodId, 0);
        boolean forall_91 = true;

        ret = testObj.getEnrolledStudents();
        for(int i : is_0)
        {
            forall_91 = forall_91 && ((ret.get(i).compareTo(ret.get(i + 1)) >= 0));
        }
        Assert.assertTrue(forall_91);

        setUp();
    }
    /*End generated tests*/
}
