package com.teampc.admin;

import java.util.List;
import com.teampc.admin.course.*;

/**
 *
 * This class contains information that identifies a Student.
 *
 * It contains information such as courses a Student is enrolled in.
 */
public abstract class Student extends User {
  public List<Enrolled> enrolledCourses;

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
