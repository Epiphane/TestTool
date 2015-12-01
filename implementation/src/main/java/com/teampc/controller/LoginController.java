package com.teampc.controller;

import com.teampc.model.admin.User;
import com.teampc.model.admin.access.UserSession;
import com.teampc.utils.FXUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ToolBar;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import javafx.scene.control.*;

import java.io.IOException;

/**
 * LoginController controls all events that happen on the login page.
 * Clicking on the loginbutton brings the user to the home toolbar and clicking on the register hyperlink brings
 * the user to the registration page.
 * @author Greg Sawers gsawers (gsawers@calpoly.edu)
 */
@Slf4j
public class LoginController {

   private Stage primaryStage;

   /**
    * Corresponds to the login button on the login screen.
    */
   @FXML private Button loginbutton;

   /**
    * Corresponds to the password field on the login screen.
    */
   @FXML private PasswordField passwordField;

   /**
    * Corresponds to the username textfield on the login screen
    */
   @FXML private TextField username;

   /**
    * Corresponds to the register hyperlink on the login screen.
    */
   @FXML private Hyperlink register;

   /**
    * Corresponds to the invalid login information pop up on the screen.
    */
   @FXML private Label invalidText;

   /**
    *  Handles clicks of the login button
    * @param event Clicking of the login button
    * @throws IOException
    */
   @FXML
   void onLoginClick(ActionEvent event) throws IOException {

      /**
       * If the username corresponds to an existing user and the password is correct for that user, login to the app.
       */
      if(UserSession.login(username.getText(), passwordField.getText())) {
         User user = UserSession.getLoggedInUser();

         log.debug(user.getDisplayName());

         FXUtils.switchToScreenAndConfigureController(primaryStage, "navbar.fxml", NavBarController::setPrimaryStage);
      }
      /**
       * Otherwise tell the user their login information is incorrect
       */
      else{
         invalidText.setVisible(true);
      }
   }

   /**
    * Handles clicks of the New User hyperlink
    * @param event Clicking on the new user hyperlink
    * @throws IOException
    */
   @FXML
   void onRegisterClick(ActionEvent event) throws IOException{
      invalidText.setVisible(false);
      showLayout("register.fxml");
   }

   private void showLayout(String resource) throws IOException {
      Stage stage = new Stage();
      Parent parent = FXMLLoader.load(getClass().getClassLoader().getResource(resource));

      Scene scene = new Scene(parent);
      stage.setScene(scene);
      stage.show();
   }
}
