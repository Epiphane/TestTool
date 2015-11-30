package com.teampc;

import com.teampc.controller.LoginController;
import com.teampc.controller.NavBarController;
import com.teampc.model.admin.User;
import com.teampc.utils.FXUtils;
import javafx.application.Application;
import javafx.scene.layout.Pane;
import javafx.scene.control.ToolBar;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.scene.Scene;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Scanner;
import java.util.function.Consumer;

public class TestToolMain extends Application {

   /**
    * Collection of all users registered with the system. (Use until user accounts are hooked into DB).
    */

   public static void main(String... args) {
      launch(args);
   }

//        VBox vbox = FXMLLoader.load(getClass().getClassLoader().getResource("login.fxml"));

//        primaryStage.setScene(new Scene(vbox));
//        primaryStage.setScene(new Scene(toolbar));
        // TODO: ^ show that thing after logging in
//        primaryStage.show();
   @Override
   public void start(Stage primaryStage) throws IOException {

      FXUtils.switchToScreenAndConfigureController(primaryStage, "login.fxml", LoginController::setPrimaryStage);
//      FXUtils.switchToScreenAndConfigureController(primaryStage, "navbar.fxml",
//         controller -> ((NavBarController) controller).setPrimaryStage(primaryStage));
   }

}
