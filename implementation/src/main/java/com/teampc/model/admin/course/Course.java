package com.teampc.model.admin.course;

import lombok.Data;
import lombok.Builder;

import java.util.List;
import com.teampc.model.admin.*;
import com.teampc.model.test.Test;

/**
 *
 * This class contains information to uniquely identify a Course.
 *
 * It contains information such as course title, section, instructor.
 * Methods in this class will be used to modify any related fields.
 */
 @Data
public abstract class Course {
  /** Teacher assigned to the course **/
  public Teacher teacher;
  /** Course title **/
  public String title;
  /** Term when the course occurs **/
  public Term term;
  /** Year when the course occurs **/
  public int year;
  /** Section number to identify the course **/
  public int section;
  /**
   * The list of tests associated with the course
   */
  public List<Test> testList;

  /**
   * Course will be constructed with the title, term, year, and section
   */
  public Course(String title, Term term, int year, int section) {

  }

  /**
   * Assigns a teacher to a course.
   */
  public abstract void assignTeacher(Teacher teacher);

  /**
   * Gets a list of students enrolled in this course
   */
  public abstract List<Student> getEnrolledStudents();
}
