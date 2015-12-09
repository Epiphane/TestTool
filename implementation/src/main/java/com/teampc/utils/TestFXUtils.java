package com.teampc.utils;

import com.teampc.controller.test.TakeTestController;
import com.teampc.controller.test.TestEvent;
import com.teampc.model.test.Test;
import javafx.stage.Stage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

/**
 * TestFXUtil is a utility class with methods that may be used in various parts of the application.
 * These methods are intended for use with JavaFX.
 *
 * @author Jameson Li (jrli@calpoly.edu)
 */
public class TestFXUtils {
   /** Provides logging **/
   private static final Logger LOG = LoggerFactory.getLogger(TestFXUtils.class);

   /**
    * Places a test viewer on the given stage. The test and test event must be specified.
    */
   public static void openTestViewer(Stage stage, Test test, TestEvent event) {
      try {
         FXUtils.switchToScreenAndConfigureController(stage, "take-test.fxml",
            (controller, rtnStage) -> {
               try {
                  TakeTestController takeTestController = (TakeTestController) controller;
                  takeTestController.setTest(test);
                  takeTestController.setEventType(event);
               } catch (Exception e) {
                  LOG.error(e.getMessage());
               }
            });
      }catch (IOException e) {
         LOG.error(e.getMessage());
      }
   }
}
