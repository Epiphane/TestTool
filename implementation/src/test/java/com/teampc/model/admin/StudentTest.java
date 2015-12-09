package com.teampc.model.admin;

import com.rits.cloning.Cloner;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import testing.CombinationSupport;
import testing.JavaTestUtility;
import testing.runner.SpestRunner;

import java.io.File;
import java.util.List;

import static org.junit.Assert.assertTrue;
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
   private File rootDirectory = new File(".");
   private File sourceFile = new File("src/main/java/com/teampc/model/admin/Student.java");
   private JavaTestUtility javaTestUtility = new JavaTestUtility(rootDirectory, sourceFile, false);
   private com.teampc.model.admin.Student testObj;

   @Test
   public void addCourseTest_0() throws Exception
   {
      int testComboIndex;

      String methodId = "addCourse_com.teampc.model.admin.course.Course";
      List<com.teampc.model.admin.course.Course> testPoints_0 = javaTestUtility.getSampleObjects(testObj, methodId, "course", com.teampc.model.admin.course.Course.class);
      int[][] combinations = CombinationSupport.getCombinations(testPoints_0.size());

      com.teampc.model.admin.course.Course param_0;
      for(testComboIndex = 0; testComboIndex < combinations.length; testComboIndex++)
      {
         param_0 = testPoints_0.get(combinations[testComboIndex][0]);

         testObj.addCourse(param_0);
         assertTrue(testObj.enrolledCourses.contains(param_0));

         setUp();
      }
   }

   @Test
   public void removeCourseTest_1() throws Exception
   {
      java.util.ArrayList<com.teampc.model.admin.course.Course> enrolledCourses = cloner.deepClone(getFieldValue(testObj, "enrolledCourses", java.util.ArrayList.class));

      int testComboIndex;

      String methodId = "removeCourse_com.teampc.model.admin.course.Course";
      List<com.teampc.model.admin.course.Course> testPoints_0 = javaTestUtility.getSampleObjects(testObj, methodId, "course", com.teampc.model.admin.course.Course.class);
      int[][] combinations = CombinationSupport.getCombinations(testPoints_0.size());

      com.teampc.model.admin.course.Course param_0;
      for(testComboIndex = 0; testComboIndex < combinations.length; testComboIndex++)
      {
         param_0 = testPoints_0.get(combinations[testComboIndex][0]);

         testObj.removeCourse(param_0);
         assertTrue(!testObj.enrolledCourses.contains(param_0));

         setUp();
      }
   }
    /*End generated tests*/
}
