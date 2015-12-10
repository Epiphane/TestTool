package com.teampc.model.admin;

import com.rits.cloning.Cloner;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import testing.CombinationSupport;
import testing.JavaTestUtility;
import testing.runner.SpestRunner;

import java.io.File;
import java.util.List;

@RunWith(SpestRunner.class)
public class TeacherSpestTest
{
   @Before
   public void setUp()
   {
      testObj = (com.teampc.model.admin.Teacher)javaTestUtility.getSampleObject(clazz);
      testObj.getCourses().forEach(course -> course.setTeacher(testObj));
   }

   /*Start generated tests*/
   private Class clazz = com.teampc.model.admin.Teacher.class;

   private Cloner cloner = new Cloner();
   private File rootDirectory = new File(".");
   private File sourceFile = new File("src/main/java/com/teampc/model/admin/Teacher.java");
   private JavaTestUtility javaTestUtility = new JavaTestUtility(rootDirectory, sourceFile, false);
   private com.teampc.model.admin.Teacher testObj;

   @Test
   public void addCourseTest_0() throws Exception
   {
      int testComboIndex;

      String methodId = "addCourse_com.teampc.model.admin.course.Course";
      List<com.teampc.model.admin.course.Course> testPoints_0 = javaTestUtility.getSampleObjects(testObj, methodId, "course", com.teampc.model.admin.course.Course.class);
      testPoints_0.forEach(course -> course.setTeacher(testObj));
      int[][] combinations = CombinationSupport.getCombinations(testPoints_0.size());

      com.teampc.model.admin.course.Course param_0;
      for(testComboIndex = 0; testComboIndex < combinations.length; testComboIndex++)
      {
         param_0 = testPoints_0.get(combinations[testComboIndex][0]);

         testObj.addCourse(param_0);
         Assert.assertTrue(testObj.getCourses() != null);
         Assert.assertTrue(testObj.getCourses().size() > 0);
         Assert.assertTrue(testObj.isAssignedToAllCourses());
         setUp();
      }
   }

   @Test
   public void removeCourseTest_1() throws Exception
   {
      int testComboIndex;

      String methodId = "removeCourse_com.teampc.model.admin.course.Course";
      List<com.teampc.model.admin.course.Course> testPoints_0 = javaTestUtility.getSampleObjects(testObj, methodId, "course", com.teampc.model.admin.course.Course.class);
      int[][] combinations = CombinationSupport.getCombinations(testPoints_0.size());

      com.teampc.model.admin.course.Course param_0;
      for(testComboIndex = 0; testComboIndex < combinations.length; testComboIndex++)
      {
         param_0 = testPoints_0.get(combinations[testComboIndex][0]);

         testObj.removeCourse(param_0);
         Assert.assertTrue(testObj.getCourses() != null);
         Assert.assertTrue(testObj.isAssignedToAllCourses());
         setUp();
      }
   }

   @Test
   public void teachesCourseTest_2() throws Exception
   {
      int testComboIndex;

      String methodId = "teachesCourse_com.teampc.model.admin.course.Course";
      List<com.teampc.model.admin.course.Course> testPoints_0 = javaTestUtility.getSampleObjects(testObj, methodId, "course", com.teampc.model.admin.course.Course.class);
      int[][] combinations = CombinationSupport.getCombinations(testPoints_0.size());

      com.teampc.model.admin.course.Course param_0;
      for(testComboIndex = 0; testComboIndex < combinations.length; testComboIndex++)
      {
         param_0 = testPoints_0.get(combinations[testComboIndex][0]);

         testObj.teachesCourse(param_0);
         Assert.assertTrue(testObj.getCourses() != null);
         Assert.assertTrue(testObj.isAssignedToAllCourses());
         setUp();
      }
   }
    /*End generated tests*/
}
