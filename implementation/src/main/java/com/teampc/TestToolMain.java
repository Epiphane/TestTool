package com.teampc;

import javafx.application.Application;
import javafx.scene.layout.Pane;
import javafx.scene.control.ToolBar;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Scene;

import java.io.IOException;

public class TestToolMain extends Application {
    public static void main(String... args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws IOException {
        ToolBar toolbar = FXMLLoader.load(getClass().getClassLoader().getResource("navbar.fxml"));

        primaryStage.setScene(new Scene(toolbar));
        primaryStage.show();
    }
}