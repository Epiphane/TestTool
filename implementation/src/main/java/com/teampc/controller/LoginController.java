package com.teampc.controller;

import com.teampc.model.admin.User;
import com.teampc.model.admin.access.UserSession;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * Created by Greg on 11/9/15.
 */
public class LoginController {

    @FXML
    void onLoginClick(ActionEvent event) throws IOException {
        UserSession session = new UserSession(new User());
        session.login();

        Stage stage = new Stage();
        Pane navbar = FXMLLoader.load(getClass().getClassLoader().getResource("navbar.fxml"));

        Scene scene = new Scene(navbar);
        stage.setScene(scene);
        stage.show();


    }
}
