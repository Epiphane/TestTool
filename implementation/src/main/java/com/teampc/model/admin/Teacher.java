package com.teampc.model.admin;

import com.teampc.model.admin.course.Course;

import java.util.List;

/**
 * This class contains all the information that identifies a User as a Teacher.
 *
 * Created by Greg.
 */
public abstract class Teacher extends User {

    /**
     * List of classes the Teacher is instructing.
     * @Data
     */
    private List/*<Course>*/ courses;

    /**
     * Create a new instance of a Teacher
     *
     * @param username The username of the Teacher
     * @param first  The Teacher's first name
     * @param last  The Teacher's last name
     */
    public Teacher(String username, String first, String last){
        super(username, first, last);
    }

    /**
     * Add the course to the Teacher's list of courses, and, if necessary, set the courses teacher to this teacher.
     * @param course Which course to add
      pre: courses != null && isAssignedToAllCourses()

      post: courses != null && courses.size() > 0 && isAssignedToAllCourses()

     */
    public abstract void addCourse(Course course);

    /**
     * Remove the course from the Teacher's list of courses.
     * @param course Which course to remove.
      pre: courses != null && isAssignedToAllCourses()

      post: courses != null && isAssignedToAllCourses()

     */
    public abstract void removeCourse(Course course);

    /**
     * Determines if teacher is currently teaching a given course.
     * @param course which to look for
      pre: courses != null && isAssignedToAllCourses()

      post: courses != null && isAssignedToAllCourses()
    */
    public abstract void teachesCourse(Course course);


    private boolean isAssignedToAllCourses() {
        for (int i = 0; i < courses.size(); i++) {
            if (((Course)courses.get(i)).teacher != this) {
                return false;
            }
        }
        return true;
    }

}
