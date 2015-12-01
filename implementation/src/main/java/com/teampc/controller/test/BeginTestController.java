package com.teampc.controller.test;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

public class BeginTestController extends TestSectionController {
   private static final Logger LOG = LoggerFactory.getLogger(BeginTestController.class);

   @FXML
   private ResourceBundle resources;

   /**
    * Initializes the Test Section
    */
   @FXML
   public void initialize(URL fxmlFileLocation, ResourceBundle resources) {
   }

   @FXML
   void onBegin(ActionEvent event) throws IOException {
      parent.onBegin(event);
   }
}
