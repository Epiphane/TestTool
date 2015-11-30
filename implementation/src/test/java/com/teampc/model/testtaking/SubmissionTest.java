package com.teampc.model.testtaking;

import com.teampc.model.testtaking.Submission;
import testing.CombinationSupport;

import org.junit.runner.RunWith;
import testing.runner.SpestRunner;
import org.junit.Before;
import org.junit.Test;
import org.junit.Assert;

import testing.JavaTestUtility;
import format.ClassNameFormat;
import com.teampc.model.testtaking.Submission;

import java.io.File;
import com.rits.cloning.Cloner;

import java.util.*;

import static testing.JavaTestUtility.getFieldValue;

@RunWith(SpestRunner.class)
public class SubmissionTest
{
    @Before
    public void setUp()
    {
        testObj = (com.teampc.model.testtaking.Submission)javaTestUtility.getSampleObject(clazz);

    }

    /*Start generated tests*/
    private Class clazz = com.teampc.model.testtaking.Submission.class;

    private Cloner cloner = new Cloner();
    private File rootDirectory = new File("/home/andy/dev/school/TestTool/implementation");
    private File sourceFile = new File("/home/andy/dev/school/TestTool/implementation/src/main/java/com/teampc/model/testtaking/Submission.java");
    private JavaTestUtility javaTestUtility = new JavaTestUtility(rootDirectory, sourceFile, false);
    private com.teampc.model.testtaking.Submission testObj;    /*End generated tests*/
}
