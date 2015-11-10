package com.teampc.view;

import javafx.scene.Node;
import javafx.scene.control.ListCell;

/**
 * Created by james on 11/9/15.
 */
public class SimpleCellFactory<S> extends ListCell<S> {
   @Override
   public void updateItem(S item, boolean empty) {
      super.updateItem(item, empty);
      if (item != null) {
         setGraphic((Node) item);
      }
   }
}
