package com.teampc.admin;

import lombok.Data;
import lombok.Builder;

import com.teampc.admin.course.*;
import java.util.List;

/**
 *
 * This class contains information that identifies a Student.
 *
 * It contains information such as courses a Student is enrolled in.
 */
 @Data
public abstract class Student extends User {
  /** Student enrolled courses list **/
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
   */
  public abstract void enrollCourse(Course course);

  /**
   * Removes a Student from the specified course.
   * The method will remove the Enrolled instance
   * from the enrolledCourses list.
   */
  public abstract void removeCourse(Course course);
}
