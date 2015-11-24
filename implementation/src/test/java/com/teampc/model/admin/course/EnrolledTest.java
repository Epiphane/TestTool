package com.teampc.model.admin.course;

import com.teampc.model.admin.course.Enrolled;
import testing.CombinationSupport;

import org.junit.runner.RunWith;
import testing.runner.SpestRunner;
import org.junit.Before;
import org.junit.Test;
import org.junit.Assert;

import testing.JavaTestUtility;
import format.ClassNameFormat;
import com.teampc.model.admin.course.Enrolled;

import java.io.File;
import com.rits.cloning.Cloner;

import java.util.*;

import static testing.JavaTestUtility.getFieldValue;

@RunWith(SpestRunner.class)
public class EnrolledTest
{
    @Before
    public void setUp()
    {
        testObj = (com.teampc.model.admin.course.Enrolled)javaTestUtility.getSampleObject(clazz);

    }

    /*Start generated tests*/
    private Class clazz = com.teampc.model.admin.course.Enrolled.class;

    private Cloner cloner = new Cloner();
    private File rootDirectory = new File("/Users/james/TestTool/implementation");
    private File sourceFile = new File("/Users/james/TestTool/implementation/src/main/java/com/teampc/model/admin/course/Enrolled.java");
    private JavaTestUtility javaTestUtility = new JavaTestUtility(rootDirectory, sourceFile, false);
    private com.teampc.model.admin.course.Enrolled testObj;    /*End generated tests*/
}
