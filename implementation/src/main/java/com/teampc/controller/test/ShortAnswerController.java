package com.teampc.controller.test;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ShortAnswerController extends TestSectionController {
   private static final Logger LOG = LoggerFactory.getLogger(ShortAnswerController.class);

   @FXML
   private ResourceBundle resources;

   @FXML
   private TextArea response;

   /**
    * Initializes the Test Section
    */
   @FXML
   public void initialize(URL fxmlFileLocation, ResourceBundle resources) {
   }

   public void onLeave() {
      LOG.info("Saving response: " + response.getText());
   }
}
