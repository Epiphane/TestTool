package com.teampc.view;

import com.teampc.controller.TestRowController;
import com.teampc.model.test.Test;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.layout.Pane;

import java.io.IOException;

/**
 * Created by james on 11/9/15.
 */
public class RowView extends Pane {
   @FXML
   private Node testRow;

   public RowView(Test test, String resource) {
      FXMLLoader loader = new FXMLLoader(
            getClass().getClassLoader().getResource(resource));
      loader.setController(new TestRowController(test));

      try {
         testRow = (Pane) loader.load();
      } catch (IOException e) {
         e.printStackTrace();
      }

      getChildren().add(testRow);
   }
}
