package com.teampc.controller;

import com.teampc.view.SimpleCellFactory;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;

import java.net.URL;
import java.util.ResourceBundle;

public abstract class ListViewController<T> implements Initializable {
   @FXML
   private ListView<T> viewTestList;
   protected ObservableList<T> data;

   protected void initView() {
      viewTestList.setItems(data);

      viewTestList.setCellFactory(list -> new SimpleCellFactory<>());
   }
}
