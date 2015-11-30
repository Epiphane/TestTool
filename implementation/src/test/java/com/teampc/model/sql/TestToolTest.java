package com.teampc.model.sql;

import com.teampc.model.sql.TestTool;
import testing.CombinationSupport;

import org.junit.runner.RunWith;
import testing.runner.SpestRunner;
import org.junit.Before;
import org.junit.Test;
import org.junit.Assert;

import testing.JavaTestUtility;
import format.ClassNameFormat;
import com.teampc.model.sql.TestTool;

import java.io.File;
import com.rits.cloning.Cloner;

import java.util.*;

import static testing.JavaTestUtility.getFieldValue;

@RunWith(SpestRunner.class)
public class TestToolTest
{
    @Before
    public void setUp()
    {
        testObj = (com.teampc.model.sql.TestTool)javaTestUtility.getSampleObject(clazz);

    }

    /*Start generated tests*/
    private Class clazz = com.teampc.model.sql.TestTool.class;

    private Cloner cloner = new Cloner();
    private File rootDirectory = new File("/home/andy/dev/school/TestTool/implementation");
    private File sourceFile = new File("/home/andy/dev/school/TestTool/implementation/src/main/java/com/teampc/model/sql/TestTool.java");
    private JavaTestUtility javaTestUtility = new JavaTestUtility(rootDirectory, sourceFile, false);
    private com.teampc.model.sql.TestTool testObj;    /*End generated tests*/
}
