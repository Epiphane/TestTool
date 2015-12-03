package com.teampc.view;

import com.teampc.controller.test.TestRowController;
import com.teampc.model.test.Test;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.layout.Pane;

import java.io.IOException;

import lombok.Getter;

/**
 * Created by james on 11/9/15.
 */
public class RowView extends Pane {
   @FXML
   private Node testRow;

   @Getter
   private Object controller;

   public RowView(Object controller, String resource) {
      this.controller = controller;

      FXMLLoader loader = new FXMLLoader(
            getClass().getClassLoader().getResource(resource));
      loader.setController(controller);

      try {
         testRow = (Pane) loader.load();
      } catch (IOException e) {
         e.printStackTrace();
      }

      getChildren().add(testRow);
   }
}
