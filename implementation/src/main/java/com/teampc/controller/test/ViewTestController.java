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

  ObservableList/*<TestRow>*/ data = FXCollections.observableArrayList(
//        new TestRow(new Test("307 Midterm 1", new Date(11), new Date(11), "307")),
//        new TestRow(new Test("307 Midterm 2", new Date(11), new Date(11), "307"))
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
