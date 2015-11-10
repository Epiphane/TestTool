package com.teampc.view;

import com.teampc.controller.TestRowController;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.layout.Pane;

import java.io.IOException;

/**
 * Created by james on 11/9/15.
 */
public class TestRow extends Pane {
   @FXML
   private Node testRow;

   public TestRow(String name, boolean isCompleted, boolean isPublic) {
      FXMLLoader loader = new FXMLLoader(
            getClass().getClassLoader().getResource("view-test-row.fxml"));
      loader.setController(new TestRowController(name, isCompleted, isPublic));

      try {
         testRow = (Pane) loader.load();
      } catch (IOException e) {
         e.printStackTrace();
      }

      getChildren().add(testRow);
   }
}
