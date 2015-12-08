package com.teampc.controller.test;

import com.teampc.model.test.Test;
import com.teampc.view.SimpleCellFactory;
//import com.teampc.view.TestRow;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;

import java.net.URL;
import java.util.Date;
import java.util.ResourceBundle;

public class ViewTestController implements Initializable {
  @FXML
  private ListView/*<TestRow>*/ viewTestList;

  ObservableList/*<TestRow>*/ data = FXCollections.observableArrayList();

  @Override
  public void initialize(URL location, ResourceBundle resources) {
     initTestList();
  }

  private void initTestList() {
     viewTestList.setItems(data);

     viewTestList.setCellFactory(list -> new SimpleCellFactory<>());
  }
}
