package com.teampc.view;

import com.teampc.controller.test.TestRowController;
import com.teampc.model.test.Test;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.layout.Pane;

import java.io.IOException;

import lombok.Getter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by james on 11/9/15.
 */
public class RowView<T> extends Pane {
   private static final Logger LOG = LoggerFactory.getLogger(TestRowController.class);

   @FXML
   private Node testRow;

   @Getter
   private T controller;

   public RowView(T controller, String resource) {
      this.controller = controller;

      FXMLLoader loader = new FXMLLoader(
            getClass().getClassLoader().getResource(resource));
      loader.setController(controller);

      try {
         testRow = (Pane) loader.load();
         getChildren().add(testRow);
      } catch (IOException e) {
         LOG.error("could not add row", e);
      }
   }
}
