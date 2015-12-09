package com.teampc.model.admin.course;

import lombok.Data;
import lombok.Builder;

import com.teampc.model.admin.*;

/**
 * Represents a relation between a Student and a Course.
 * Each student may have many enrolled courses and each
 * course may have many students.
 *
 * @author Jameson Li (jrli@calpoly.edu)
 */
 @Data
public class Enrolled {

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
