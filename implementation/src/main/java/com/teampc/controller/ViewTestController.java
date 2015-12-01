package com.teampc.controller;

import com.teampc.model.test.Test;
import com.teampc.view.SimpleCellFactory;
import com.teampc.view.TestRow;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;

import java.net.URL;
import java.util.Date;
import java.util.ResourceBundle;

import com.teampc.dao.*;
import java.util.*;

public class ViewTestController implements Initializable {
   @FXML
   private ListView<TestRow> viewTestList;

   ObservableList<TestRow> data; 

   public ViewTestController() {
   List<TestRow> rows = new ArrayList<TestRow>();
      for (Test t: TestDAO.getInstance().fetchAll()) {
         rows.add(new TestRow(t));
      }
      data = FXCollections.observableArrayList(rows);
   }

   @Override
   public void initialize(URL location, ResourceBundle resources) {
      initTestList();
   }

   private void initTestList() {
      viewTestList.setItems(data);

      viewTestList.setCellFactory(list -> new SimpleCellFactory<>());
   }
}
