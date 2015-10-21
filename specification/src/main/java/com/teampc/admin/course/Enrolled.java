package com.teampc.admin.course;

import com.teampc.admin.*;

/**
 * Represents a relation between a Student and a Course.
 * Each student may have many enrolled courses and each
 * course may have many students.
 */
public abstract class Enrolled {

  public Student student;
  public Course course;

  /**
   * Enrolled will be constructed with Student and Course
   */
  public Enrolled(Student student, Course course) {

  }
}
