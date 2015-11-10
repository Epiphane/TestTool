package com.teampc.utils;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.function.BiConsumer;
import java.util.function.Consumer;

/**
 * Created by adufrene on 11/9/15.
 */
public class FXUtils {

   public static <T> Stage newScreenAndConfigureController(String layoutFilename, BiConsumer<T, Stage> controllerConfigurationFunction) throws IOException {
      FXMLLoader loader = new FXMLLoader(FXUtils.class.getClassLoader().getResource(layoutFilename));
      Stage stage = new Stage();

      stage.setScene(new Scene(loader.load()));

      controllerConfigurationFunction.accept(loader.getController(), stage);

      stage.show();
      return stage;
   }

   public static Stage switchToScreen(Stage stage, String layoutFilename) throws IOException {
      return switchToScreenAndConfigureController(stage, layoutFilename, x -> {});
   }

   public static <T> Stage switchToScreenAndConfigureController(Stage stage, String layoutFilename, Consumer<T> controllerConfigurationFunction) throws IOException {
      FXMLLoader loader = new FXMLLoader(FXUtils.class.getClassLoader().getResource(layoutFilename));

      stage.setScene(new Scene(loader.load()));

      controllerConfigurationFunction.accept(loader.getController());

      stage.show();
      return stage;
   }
}
