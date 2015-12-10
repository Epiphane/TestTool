package com.teampc.model.admin;

import com.teampc.model.admin.course.Course;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.ArrayList;
import java.util.List;

import static java.util.stream.Collectors.toList;

/**
 * Teacher contains all the information that identifies a User as a Teacher.
 *
 * @author Greg Sawers (gsawers@calpoly.edu)
 */
@Data
@EqualsAndHashCode(callSuper=true)
public class Teacher extends User {

   /**
    * List of classes the Teacher is instructing.
    */
   private List<Course> courses = new ArrayList<>();

   /**
    * Create a new instance of a Teacher
    *
    * @param username The username of the Teacher
    * @param first  The Teacher's first name
    * @param last  The Teacher's last name
    * @param pass The Teacher's password
    */

   public Teacher(String username, String first, String last, String pass){
      super(username, first, last, pass);
   }

   public Teacher(String username, String first, String last, String pass, boolean admin) {
      super(username, first, last, pass, admin);
   }

    /**
     * Copy constructor
     */
    public Teacher(Teacher teacher) {
       super(teacher);
       this.courses = teacher.courses.stream().map(Course::new).collect(toList());
    }

   @Override
   public <T> T accept(Visitor<T> visitor) {
      return visitor.visitTeacher(this);
   }


   /**
    * Add the course to the Teacher's list of courses, and, if necessary, set the courses teacher to this teacher.
    * @param course Which course to add
   pre: this.getCourses() != null && this.isAssignedToAllCourses() && this.equals(course.getTeacher())

   post: this.getCourses() != null && this.getCourses().size() > 0 && this.isAssignedToAllCourses()

    */
   public void addCourse(Course course) {

      /**
       * Avoids duplicates
       */
      if(!courses.contains(course))
         courses.add(course);
   }

   /**
    * Remove the course from the Teacher's list of courses.
    * @param course Which course to remove.
   pre: this.getCourses() != null && this.isAssignedToAllCourses()

   post: !this.getCourses().contains(course) && this.isAssignedToAllCourses()

    */

   public void removeCourse(Course course) {
      courses.remove(course);
   }

   /**
    * Determines if teacher is currently teaching a given course.
    * @param course which to look for
    * @return Whether the Teacher teaches this course
   pre: this.getCourses() != null && this.isAssignedToAllCourses()

   post: this.getCourses() != null && this.isAssignedToAllCourses()
    */

   public boolean teachesCourse(Course course) {
      return courses.contains(course);
   }


   /**
    *
    * @return
    */
   boolean isAssignedToAllCourses() {
      return this.courses.stream().allMatch(course -> this.equals(course.getTeacher()));
   }

}
