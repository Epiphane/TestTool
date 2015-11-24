package com.teampc.model.admin.access;

import com.teampc.model.admin.access.UserSession;
import testing.CombinationSupport;

import org.junit.runner.RunWith;
import testing.runner.SpestRunner;
import org.junit.Before;
import org.junit.Test;
import org.junit.Assert;

import testing.JavaTestUtility;
import format.ClassNameFormat;
import com.teampc.model.admin.access.UserSession;

import java.io.File;
import com.rits.cloning.Cloner;

import java.util.*;

import static testing.JavaTestUtility.getFieldValue;

@RunWith(SpestRunner.class)
public class UserSessionTest
{
    @Before
    public void setUp()
    {
        testObj = (com.teampc.model.admin.access.UserSession)javaTestUtility.getSampleObject(clazz);

    }

    /*Start generated tests*/
    private Class clazz = com.teampc.model.admin.access.UserSession.class;

    private Cloner cloner = new Cloner();
    private File rootDirectory = new File("/Users/james/TestTool/implementation");
    private File sourceFile = new File("/Users/james/TestTool/implementation/src/main/java/com/teampc/model/admin/access/UserSession.java");
    private JavaTestUtility javaTestUtility = new JavaTestUtility(rootDirectory, sourceFile, false);
    private com.teampc.model.admin.access.UserSession testObj;
    @Test
    public void loginTest_0() throws Exception
    {
        boolean loggedIn = getFieldValue(testObj, "loggedIn", java.lang.Boolean.class);
        com.teampc.model.admin.User loggedInUser = cloner.deepClone(getFieldValue(testObj, "loggedInUser", com.teampc.model.admin.User.class));

        int testComboIndex;

        String methodId = "login_com.teampc.model.admin.User";
        List<com.teampc.model.admin.User> testPoints_0 = javaTestUtility.getSampleObjects(testObj, methodId, "user", com.teampc.model.admin.User.class);
        int[][] combinations = CombinationSupport.getCombinations(testPoints_0.size());

        com.teampc.model.admin.User param_0;
        for(testComboIndex = 0; testComboIndex < combinations.length; testComboIndex++)
        {
            param_0 = testPoints_0.get(combinations[testComboIndex][0]);

            testObj.login(param_0);
            Assert.assertTrue(loggedIn == true);
            Assert.assertTrue(loggedInUser.equals(param_0));
            setUp();
        }
    }

    @Test
    public void getLoggedInUserTest_1() throws Exception
    {
        boolean loggedIn = getFieldValue(testObj, "loggedIn", java.lang.Boolean.class);
        com.teampc.model.admin.User loggedInUser = cloner.deepClone(getFieldValue(testObj, "loggedInUser", com.teampc.model.admin.User.class));


        String methodId = "getLoggedInUser";

        testObj.getLoggedInUser();
        Assert.assertTrue(loggedIn);
        Assert.assertTrue(loggedInUser != null);
        setUp();
    }

    @Test
    public void endUserSessionTest_2() throws Exception
    {
        boolean loggedIn = getFieldValue(testObj, "loggedIn", java.lang.Boolean.class);
        com.teampc.model.admin.User loggedInUser = cloner.deepClone(getFieldValue(testObj, "loggedInUser", com.teampc.model.admin.User.class));


        String methodId = "endUserSession";

        testObj.endUserSession();
        Assert.assertTrue(!(loggedIn));
        Assert.assertTrue(loggedInUser == null);
        setUp();
    }
    /*End generated tests*/
}
