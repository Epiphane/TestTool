package com.teampc.example;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.fxml.FXMLLoader;

import java.io.IOException;

public class ExampleMain extends Application {
    public static void main(String... args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws IOException { 
        // Load the fxml file and set the pane as the "Scene"
        // getClass().getResource(...) will load from the resources relative to the current package
        // hence the "example.fxml" file is located at the package level of com.teampc.example
        Pane pane = FXMLLoader.load(getClass().getResource("example.fxml"));

        primaryStage.setScene(new Scene(pane));
        primaryStage.show();
    }
}
