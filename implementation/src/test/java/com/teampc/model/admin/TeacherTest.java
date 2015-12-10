package com.teampc.model.admin;

import com.google.common.annotations.VisibleForTesting;
import com.teampc.model.admin.course.Course;
import com.teampc.model.admin.course.Term;

import org.junit.Test;

import static org.junit.Assert.assertEquals;


/**
 * Created by Greg on 12/9/15.
 */
public class TeacherTest {


   @Test
   public void testAddCourse(){
      Teacher teacher = new Teacher("test", "test", "test", "test", false);
      Course course = new Course("Test", Term.Fall, 2015, 01);
      teacher.addCourse(course);
      assertEquals(teacher.getCourses().contains(course));
      assertEquals(teacher.getCourses().size() > 0, true);
   }

   @Test
   public void testRemoveCourse(){
      Teacher teacher = new Teacher("test", "test", "test", "test", false);
      Course course = new Course("Test", Term.Fall, 2015, 01);
      teacher.addCourse(course);
      assertEquals(teacher.getCourses().contains(course));

      teacher.removeCourse(course);
      assertEquals(teacher.getCourses().contains(course), false);
   }
}
