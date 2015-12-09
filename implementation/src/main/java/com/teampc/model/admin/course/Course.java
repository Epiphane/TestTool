package com.teampc.model.admin.course;

import lombok.Data;
import lombok.Builder;

import java.util.Collections;
import java.util.List;

import com.teampc.model.admin.*;
import com.teampc.model.test.Test;

/**
 * This class contains information to uniquely identify a Course.
 * <p>
 * It contains information such as course title, section, instructor.
 * Methods in this class will be used to modify any related fields.
 */
@Data
public class Course {
   /**
    * Teacher assigned to the course
    */
   public Teacher teacher;
   /**
    * Course title
    */
   public String title;
   /**
    * Term when the course occurs
    */
   public Term term;
   /**
    * Year when the course occurs
    */
   public int year;
   /**
    * Section number to identify the course
    */
   public int section;
   /**
    * The list of tests associated with the course
    */
   public List<Test> testList;

   /**
    * Course will be constructed with the title, term, year, and section
    */
   public Course(String title, Term term, int year, int section) {
      this.title = title;
      this.term = term;
      this.year = year;
      this.section = section;
   }

   /**
    * Assigns a teacher to a course.
    */
   public void assignTeacher(Teacher teacher) {

   }

   /**
    * Gets a list of students enrolled in this course
    *
     post:
      //
      // The return value is a list of students enrolled in this course.
      // The list is expected to be sorted by the student's name. Student
      // is expected to have compareTo properly implemented
      //
      forall(int i; i>=0 && i<return.size()-1;
         return.get(i).compareTo(return.get(i+1)) < 0)
    *
    */
   public List<Student> getEnrolledStudents() {
      return Collections.emptyList();
   }

   /**
    * Return a String representation of this course for display purposes.
    *
         pre: title != null && term != null
    */
   public String toString() {
      return "" + title + "-" + String.format("%02d", section) + ", " + term.name() + " " + year;
   }
}
