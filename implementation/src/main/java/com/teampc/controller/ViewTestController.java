package com.teampc.controller;

import com.teampc.view.SimpleCellFactory;
import com.teampc.view.TestRow;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;

import java.net.URL;
import java.util.ResourceBundle;

public class ViewTestController implements Initializable {
  @FXML
  private ListView<TestRow> viewTestList;

  ObservableList<TestRow> data = FXCollections.observableArrayList(
        new TestRow("307 Midterm 1", true, true),
        new TestRow("307 Midterm 2", true, true)
  );

  @Override
  public void initialize(URL location, ResourceBundle resources) {
     initTestList();
  }

  private void initTestList() {
     viewTestList.setItems(data);

     viewTestList.setCellFactory(list -> new SimpleCellFactory<>());
  }
}
