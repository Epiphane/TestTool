package com.teampc.controller;

import com.teampc.model.admin.User;
import com.teampc.model.admin.access.UserSession;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.ToolBar;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * Created by Greg on 11/9/15.
 */
public class LoginController {

    @FXML private javafx.scene.control.Button loginbutton;

    @FXML
    void onLoginClick(ActionEvent event) throws IOException {
        UserSession session = new UserSession(new User());
        session.login(new User());
        User user = session.getLoggedInUser();
        System.out.println(user.getDisplayName());

        Stage stage = (Stage) loginbutton.getScene().getWindow();


        ToolBar navbar = FXMLLoader.load(getClass().getClassLoader().getResource("navbar.fxml"));

        Scene scene = new Scene(navbar);
        stage.setScene(scene);
        stage.show();
    }
}
