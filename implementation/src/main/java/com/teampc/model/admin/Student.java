package com.teampc.model.admin;

import lombok.Data;
import lombok.Builder;
import lombok.EqualsAndHashCode;

import com.teampc.model.admin.course.*;

import java.util.List;

/**
 * This class contains information that identifies a Student.
 * <p>
 * It contains information such as courses a Student is enrolled in.
 */
@Data
@EqualsAndHashCode(callSuper = true)
public abstract class Student extends User implements Comparable<Student> {
   /**
    * Student enrolled courses list
    **/
   public List<Enrolled> enrolledCourses;

   /**
    * Creates a new instance of a Student
    */
   public Student(String username, String firstName, String lastName) {
      super(username, firstName, lastName);
   }

   /**
    * Enrolls a Student into the specified course.
    * The method will create a new instance of Enrolled and add
    * this instance to the enrolledCourses list.
    *
     pre:
      //
      // The enrolledCourses list for this student will not have duplicate courses
      //
      !exists (Course cother;
         enrolledCourses.contains(cother);
            cother.equals(course))
    *
     post:
      //
      // The resulting enrolledCourses list will contain the course being added
      //
      exists(Course cother;
         enrolledCourses'.contains(cother);
            cother.equals(course))
    */
   public abstract void addCourse(Course course);

   /**
    * Removes a Student from the specified course.
    * The method will remove the Enrolled instance
    * from the enrolledCourses list.
    *
    * 
     pre:
      //
      // The enrolledCourses list must already contain the course being removed
      //
      exists(Course cother;
         enrolledCourses.contains(cother);
            cother.equals(course))
    * 
    * 
    post:
      //
      // The resulting enrolledCourses list will not contain the course being removed
      //
      !exists(Course cother;
         enrolledCourses.contains(cother);
            cother.equals(course))
    * 
    */
   public abstract void removeCourse(Course course);

}
