package com.teampc.model.admin;

import com.google.common.collect.Lists;
import com.teampc.model.admin.course.Course;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

/**
 * This class contains all the information that identifies a User as a Teacher.
 *
 * Created by Greg.
 */
@Data
@EqualsAndHashCode(callSuper=true)
public class Teacher extends User {

    /**
     * List of classes the Teacher is instructing.
     */
    private List<Course> courses = Lists.newArrayList();

    /**
     * Create a new instance of a Teacher
     *
     * @param username The username of the Teacher
     * @param first  The Teacher's first name
     * @param last  The Teacher's last name
     */
    public Teacher(String username, String first, String last, String password, boolean isAdmin){
        super(username, first, last, password, isAdmin);
    }

    /**
     * Add the course to the Teacher's list of courses, and, if necessary, set the courses teacher to this teacher.
     * @param course Which course to add
      pre: this.getCourses() != null && this.isAssignedToAllCourses()

      post: this.getCourses() != null && this.getCourses().size() > 0 && this.isAssignedToAllCourses()

     */
    public void addCourse(Course course) {
       courses.add(course);
    }

    /**
     * Remove the course from the Teacher's list of courses.
     * @param course Which course to remove.
      pre: this.getCourses() != null && this.isAssignedToAllCourses()

      post: this.getCourses() != null && this.isAssignedToAllCourses()

     */
    public void removeCourse(Course course) {
       courses.remove(course);
    }

    /**
     * Determines if teacher is currently teaching a given course.
     * @param course which to look for
      pre: this.getCourses() != null && this.isAssignedToAllCourses()

      post: this.getCourses() != null && this.isAssignedToAllCourses()
    */
    public boolean teachesCourse(Course course) {
       return courses.contains(course);
    }


    boolean isAssignedToAllCourses() {
       return this.courses.stream().allMatch(course -> course.getTeacher().equals(this));
    }

}
