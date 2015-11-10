package com.teampc.controller;

import com.teampc.model.admin.User;
import com.teampc.model.admin.access.UserSession;
import com.teampc.utils.FXUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
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

    @FXML
    void onLoginClick(ActionEvent event) throws IOException {
        UserSession session = new UserSession(new User());
        session.login(new User());
        User user = session.getLoggedInUser();

        log.debug(user.getDisplayName());

        FXUtils.switchToScreenAndConfigureController(primaryStage, "navbar.fxml", NavBarController::setPrimaryStage);
    }
}
