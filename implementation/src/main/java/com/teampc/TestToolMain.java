package com.teampc;

import com.teampc.controller.NavBarController;
import com.teampc.utils.FXUtils;
import javafx.application.Application;
import javafx.scene.layout.Pane;
import javafx.scene.control.ToolBar;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Scene;

import java.io.IOException;
import java.util.function.Consumer;

public class TestToolMain extends Application {

   public static void main(String... args) {
      launch(args);
   }

   @Override
   public void start(Stage primaryStage) throws IOException {
      FXUtils.switchToScreenAndConfigureController(primaryStage, "navbar.fxml",
         controller -> ((NavBarController) controller).setPrimaryStage(primaryStage));
   }
}
