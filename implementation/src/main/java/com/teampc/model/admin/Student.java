package com.teampc.model.admin;

import lombok.Data;
import lombok.Builder;
import lombok.EqualsAndHashCode;

import com.teampc.model.admin.course.*;

import java.util.ArrayList;
import java.util.List;

/**
 * This class contains information that identifies a Student.
 * <p>
 * It contains information such as courses a Student is enrolled in.
 *
 * @author Greg Sawers (gsawers@calpoly.edu)
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class Student extends User implements Comparable<Student> {
   /**
    * Student enrolled courses list
    **/
   public ArrayList<Course> enrolledCourses = new ArrayList<Course>();

   /**
    * Creates a new instance of a Student
    */
   @Deprecated
   public Student(String username, String firstName, String lastName) {
      super(username, firstName, lastName);
   }

   @Override
   public <T> T accept(Visitor<T> visitor) {
      return visitor.visitStudent(this);
   }

   /**
    *
    * @param username The username of the student
    * @param firstName The firstname of the student
    * @param lastName The last name of the student
    * @param pass  The password of the variable
    *
    */
   public Student(String username, String firstName, String lastName, String pass){
      super(username, firstName, lastName, pass);
   }

   /**
    *
    * @param username The username of the student
    * @param firstName The firstname of the student
    * @param lastName The last name of the student
    * @param pass  The password of the variable
    * @param admin Whether or not the user is an admin
    */
   public Student(String username, String firstName, String lastName, String pass, boolean admin) {
      super(username, firstName, lastName, pass, admin);
   }

   /**
    * Enrolls a Student into the specified course.
    * The method will create a new instance of Enrolled and add
    * this instance to the enrolledCourses list.
    * @param course The course to enroll the student in
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
   public void addCourse(Course course){
      enrolledCourses.add(course);
   }

   /**
    * Removes a Student from the specified course.
    * The method will remove the Enrolled instance
    * from the enrolledCourses list.
    *
    * @param course The course to remove
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
   public void removeCourse(Course course){
      enrolledCourses.remove(course);
   }

   public int compareTo(Student other){
      return 0;
   }

}
