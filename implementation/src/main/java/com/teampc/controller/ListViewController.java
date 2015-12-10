package com.teampc.controller;

import com.teampc.view.SimpleCellFactory;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;

import java.net.URL;
import java.util.ResourceBundle;

/**
 *
 * Provides a general controller for list of items.
 * The list type is specified by the type parameter.
 *
 * @author Jameson Li (jrli@calpoly.edu)
 */
public abstract class ListViewController<T> implements Initializable {
   @FXML
   /** A list view for T**/
   private ListView<T> listView;

   /** A list of items of type T **/
   protected ObservableList<T> data;

   /** Initializes view with items of type T **/
   protected void initView() {
      if(listView == null) { return; }

      listView.setItems(data);

      listView.setCellFactory(list -> new SimpleCellFactory<>());
   }
}
