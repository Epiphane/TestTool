package com.teampc.model.admin.course;

import com.teampc.model.admin.Student;
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
import java.util.function.Function;
import java.util.stream.IntStream;

import static java.util.stream.Collectors.toList;
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
    private File rootDirectory = new File(".");
    private File sourceFile = new File("src/main/java/com/teampc/model/admin/course/Course.java");
    private JavaTestUtility javaTestUtility = new JavaTestUtility(rootDirectory, sourceFile, false);
    private com.teampc.model.admin.course.Course testObj;

    @Test
    public void getEnrolledStudentsTest_0() throws Exception
    {

        java.util.List<Student> ret;
        boolean forall_8 = true;

        ret = testObj.getEnrolledStudents();
       List<java.lang.Integer> is_0 = IntStream.range(0, ret.size() - 1).mapToObj(x -> x).collect(toList());
        for(int i : is_0)
        {
            forall_8 = forall_8 && (ret.get(i).compareTo(ret.get(i + 1)) < 0);
        }
        Assert.assertTrue(forall_8);

        setUp();
    }

    @Test
    public void toStringTest_1() throws Exception
    {

        String methodId = "toString";

        testObj.toString();
        setUp();
    }
    /*End generated tests*/
}
