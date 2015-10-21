package com.teampc.admin.course;

import com.teampc.admin.*;

/**
 * Represents a relation between a Student and a Course.
 * Each student may have many enrolled courses and each
 * course may have many students.
 */
public abstract class Enrolled {

  /** Student that is enrolled in the course **/
  public Student student;
  /** Course that the student enrolled in **/
  public Course course;

  /**
   * Enrolled will be constructed with Student and Course
   */
  public Enrolled(Student student, Course course) {

  }
}
