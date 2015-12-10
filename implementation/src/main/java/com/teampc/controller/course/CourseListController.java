package com.teampc.controller.course;

import com.teampc.controller.ListViewController;
import com.teampc.dao.CourseDAO;
import com.teampc.model.admin.access.UserSession;
import com.teampc.model.admin.course.Course;
import lombok.extern.slf4j.Slf4j;

import com.teampc.view.RowView;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

/**
 * CourseListController is a controller for a list of course views.
 *
 * @author david daelliso@calpoly.edu
 */
@Slf4j
public class CourseListController extends ListViewController<RowView<CourseRowController>> {

   /** Resource name of the fxml file **/
   private static final String RESOURCE = "course-row-display.fxml";

   @Override
   /**
    * Displays courses from CourseDAO
    *
    pre: data == null
    *
    post: data' != null &&
    testList.size() == data.size()
    */
   public void initialize(URL location, ResourceBundle resources) {

      data = FXCollections.observableArrayList();

      List<Course> courses = CourseDAO.getInstance().findCoursesForUser(UserSession.getLoggedInUser());
      courses.forEach(course -> data.add(new RowView<>(new CourseRowController(course), RESOURCE)));
      log.debug("logged in user is class: " + UserSession.getLoggedInUser().getClass() + " and has UserType " + UserSession.getLoggedInUser().getUserType());
      initView();
   }
}
