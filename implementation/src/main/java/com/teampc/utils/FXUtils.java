package com.teampc.utils;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.function.Consumer;

/**
 * Created by adufrene on 11/9/15.
 */
public class FXUtils {

   /**
    * Switches the main screen of the app to the new layout specified by the file name. Returns the new screen
    * @param stage The old screen of the app
    * @param layoutFilename filename of the fxml layout file that will be created and shown
    * @return The new screen after switching screens
    * @throws IOException yeah
    */
   public static Stage switchToScreen(Stage stage, String layoutFilename) throws IOException {
      return switchToScreenAndConfigureController(stage, layoutFilename, x -> {});
   }

   /**
    * Switches main screen of the app to the supplied layout file name and configures the layout
    * files controller according to the configuration closure supplied.
    * @param stage The old screen of the app
    * @param layoutFilename filename of the fxml layout file that will be created and shown
    * @param controllerConfigurationFunction configuration closure that is used to configure the layout's controller
    * @param <T> Type of the layout's controller
    * @return The new screen of the app after switching
    * @throws IOException again
    */
   public static <T> Stage switchToScreenAndConfigureController(Stage stage, String layoutFilename, Consumer<T> controllerConfigurationFunction) throws IOException {
      FXMLLoader loader = new FXMLLoader(FXUtils.class.getClassLoader().getResource(layoutFilename));

      stage.setScene(new Scene(loader.load()));

      controllerConfigurationFunction.accept(loader.getController());

      stage.show();
      return stage;
   }
}
