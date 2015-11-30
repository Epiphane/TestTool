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

public class ViewTestsController extends ListViewController<TestRow> {

   @Override
   public void initialize(URL location, ResourceBundle resources) {

      data = FXCollections.observableArrayList(
         new TestRow(new Test("307 Midterm 1", new Date(11), new Date(11), "307")),
         new TestRow(new Test("307 Midterm 2", new Date(11), new Date(11), "307")));

      super.initialize(location, resources);
   }

}
