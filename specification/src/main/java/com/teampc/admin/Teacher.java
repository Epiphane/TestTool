package com.teampc.admin;

import com.teampc.admin.course.Course;

import java.util.List;

/**
 * This class contains all the information that identifies a User as a Teacher
 */
public abstract class Teacher extends User {

    /**
     * List of classes the Teacher is instructing.
     */
    public List<Course> courses;

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
     */
    public abstract void addCourse(Course course);

    /**
     * Remove the course from the Teacher's list of courses.
     * @param course Which course to remove.
     */
    public abstract void removeCourse(Course course);


}
