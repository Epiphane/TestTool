package com.teampc.admin.course;

import com.teampc.admin.*;

/**
 *
 * This class contains information to uniquely identify a Course.
 *
 * It contains information such as course title, section, instructor.
 * Methods in this class will be used to modify any related fields
 */
public abstract class Course {
  public Teacher teacher;
  public String title;
  public Term term;
  public int year;
  public int section;

  /**
   * Course will be constructed with the title, term, year, and section
   */
  public Course(String title, Term term, int year, int section) {

  }

  /**
   * Assigns a teacher to a course.
   */
  public abstract void assignTeacher(Teacher teacher);
}
