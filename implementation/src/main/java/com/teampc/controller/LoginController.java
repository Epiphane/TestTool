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

import java.io.IOException;

/**
 * Created by Greg on 11/9/15.
 */
@Slf4j
public class LoginController {
   @Setter
   private Stage primaryStage;

   @FXML private javafx.scene.control.Button loginbutton;

   @FXML private javafx.scene.control.PasswordField passwordField;

   @FXML private javafx.scene.control.TextField username;

   @FXML private javafx.scene.control.Hyperlink register;

   @FXML private javafx.scene.control.Label invalidText;

   @FXML
   void onLoginClick(ActionEvent event) throws IOException {

      if(UserSession.login(username.getText(), passwordField.getText())) {
         User user = UserSession.getLoggedInUser();

         log.debug(user.getDisplayName());

         FXUtils.switchToScreenAndConfigureController(primaryStage, "navbar.fxml", NavBarController::setPrimaryStage);
      }
      else{
         invalidText.setVisible(true);
      }
   }

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
