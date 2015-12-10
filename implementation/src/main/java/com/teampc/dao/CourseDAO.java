package com.teampc.dao;

import static java.util.stream.Collectors.toList;

import com.teampc.model.admin.User;
import com.teampc.model.admin.course.Course;
import com.teampc.model.admin.User.UserType;

import java.util.List;

/**
 * CourseDAO is a data-access object for courses.
 *
 * @author david ellison daelliso@calpoly.edu
 */
public class CourseDAO extends AbstractDAO<Course> {

   private static final CourseDAO instance = new CourseDAO();

   /** Singleton getter for the CourseDAO */
   public static CourseDAO getInstance() {return instance;}

   private CourseDAO() {}

   @Override
   public Class<Course> getEntityClass() {
      return Course.class;
   }

   public List<Course> findCoursesForUser(User user) {
      System.out.println("Looking for courses for user: " + user.getUsername());
      if (UserType.TEACHER.equals(user.getUserType())) {
         //if teacher, filter for those that have teacher equal to user
         return fetchAll().stream().filter(course -> {
            return course.getTeacher().equals((user));
         }).collect(toList());
      } else {
         return fetchAll().stream().filter(course -> {
            return course.getEnrolledStudents().contains(user);
         }).collect(toList());
      }
   }


}
