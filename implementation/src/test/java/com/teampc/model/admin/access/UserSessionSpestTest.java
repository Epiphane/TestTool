package com.teampc.model.admin.access;

import com.google.common.io.Files;
import com.teampc.model.admin.User;
import testing.CombinationSupport;

import org.junit.runner.RunWith;
import testing.runner.SpestRunner;
import org.junit.Before;
import org.junit.Test;
import org.junit.Assert;

import testing.JavaTestUtility;

import java.io.File;
import com.rits.cloning.Cloner;

import java.nio.charset.Charset;
import java.util.*;

import static testing.JavaTestUtility.getFieldValue;

@RunWith(SpestRunner.class)
public class UserSessionSpestTest
{
    @Before
    public void setUp()
    {
        testObj = (com.teampc.model.admin.access.UserSession)javaTestUtility.getSampleObject(clazz);

    }

    /*Start generated tests*/
    private Class clazz = com.teampc.model.admin.access.UserSession.class;

    private Cloner cloner = new Cloner();
    private File rootDirectory = new File(".");
    private File sourceFile = new File("src/main/java/com/teampc/model/admin/access/UserSession.java");
    private JavaTestUtility javaTestUtility = new JavaTestUtility(rootDirectory, sourceFile, false);
    private com.teampc.model.admin.access.UserSession testObj;
    @Test
    public void loginTest_0() throws Exception
    {

       File userFile = new File(UserSession.USER_FILE);
       User testUser;
       if (!userFile.exists()) {
          testUser = UserSession.register("testUser", "test", "test", "user", User.UserType.STUDENT);
       } else {
          String serializedUser = Files.readFirstLine(new File(UserSession.USER_FILE), Charset.defaultCharset());
          if (serializedUser == null) {
             testUser = UserSession.register("testUser", "test", "test", "user", User.UserType.STUDENT);
          } else {
             testUser = User.fromString(serializedUser);
          }
       }

        boolean loggedIn = getFieldValue(testObj, "loggedIn", java.lang.Boolean.class);

       UserSession.login(testUser.getUsername(), testUser.getPassword());
       Assert.assertTrue(loggedIn);
       Assert.assertTrue(UserSession.getLoggedInUser().equals(testUser));
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
       UserSession.endUserSession();
        Assert.assertTrue(!UserSession.loggedIn);
        Assert.assertTrue(UserSession.loggedInUser == null);
        setUp();
    }
    /*End generated tests*/
}
