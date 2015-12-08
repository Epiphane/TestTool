package com.teampc.utils;

import com.teampc.controller.test.TakeTestController;
import com.teampc.controller.test.TestEvent;
import com.teampc.model.test.Test;
import javafx.stage.Stage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

/**
 * Created by james on 12/7/15.
 */
public class TestFXUtils {
   private static final Logger LOG = LoggerFactory.getLogger(TestFXUtils.class);

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
