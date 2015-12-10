package com.teampc.controller.course;

import com.teampc.model.admin.User;
import com.teampc.model.admin.access.UserSession;
import com.teampc.model.admin.course.Course;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.text.Text;
import lombok.extern.slf4j.Slf4j;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * CourseRowController controls interactions with the Course Row gui element
 *
 * @author david
 */
@Slf4j
public class CourseRowController implements Initializable {
   private Course course;

   public CourseRowController(Course course) {
      this.course = course;
   }


   @FXML
   /** Text field showing the name of the test **/
   private Text courseTitle;


   @FXML
   /** Text field showing the user's status in the course */
   private Text courseStatus;


   @Override
   /** Initializes the row view
       */
   public void initialize(URL location, ResourceBundle resources) {
      courseTitle.setText(course.getTitle());
      if (UserSession.getLoggedInUser().isTeacher()) {
         courseStatus.setText("Teaching");
      } else {
         courseStatus.setText("Enrolled");
      }
   }

}
