package com.teampc.model.testtaking;

import com.teampc.model.testtaking.ShortAnswerQuestionResponse;
import testing.CombinationSupport;

import org.junit.runner.RunWith;
import testing.runner.SpestRunner;
import org.junit.Before;
import org.junit.Test;
import org.junit.Assert;

import testing.JavaTestUtility;
import format.ClassNameFormat;
import com.teampc.model.testtaking.ShortAnswerQuestionResponse;

import java.io.File;
import com.rits.cloning.Cloner;

import java.util.*;

import static testing.JavaTestUtility.getFieldValue;

@RunWith(SpestRunner.class)
public class ShortAnswerQuestionResponseTest
{
    @Before
    public void setUp()
    {
        testObj = (com.teampc.model.testtaking.ShortAnswerQuestionResponse)javaTestUtility.getSampleObject(clazz);

    }

    /*Start generated tests*/
    private Class clazz = com.teampc.model.testtaking.ShortAnswerQuestionResponse.class;

    private Cloner cloner = new Cloner();
    private File rootDirectory = new File("/home/andy/dev/school/TestTool/implementation");
    private File sourceFile = new File("/home/andy/dev/school/TestTool/implementation/src/main/java/com/teampc/model/testtaking/ShortAnswerQuestionResponse.java");
    private JavaTestUtility javaTestUtility = new JavaTestUtility(rootDirectory, sourceFile, false);
    private com.teampc.model.testtaking.ShortAnswerQuestionResponse testObj;    /*End generated tests*/
}
