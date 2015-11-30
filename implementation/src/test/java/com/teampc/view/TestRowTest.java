package com.teampc.view;

import com.teampc.view.TestRow;
import testing.CombinationSupport;

import org.junit.runner.RunWith;
import testing.runner.SpestRunner;
import org.junit.Before;
import org.junit.Test;
import org.junit.Assert;

import testing.JavaTestUtility;
import format.ClassNameFormat;
import com.teampc.view.TestRow;

import java.io.File;
import com.rits.cloning.Cloner;

import java.util.*;

import static testing.JavaTestUtility.getFieldValue;

@RunWith(SpestRunner.class)
public class TestRowTest
{
    @Before
    public void setUp()
    {
        testObj = (com.teampc.view.TestRow)javaTestUtility.getSampleObject(clazz);

    }

    /*Start generated tests*/
    private Class clazz = com.teampc.view.TestRow.class;

    private Cloner cloner = new Cloner();
    private File rootDirectory = new File("/home/andy/dev/school/TestTool/implementation");
    private File sourceFile = new File("/home/andy/dev/school/TestTool/implementation/src/main/java/com/teampc/view/TestRow.java");
    private JavaTestUtility javaTestUtility = new JavaTestUtility(rootDirectory, sourceFile, false);
    private com.teampc.view.TestRow testObj;    /*End generated tests*/
}
