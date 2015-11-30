package com.teampc.controller;

import com.teampc.model.test.Test;
import com.teampc.view.RowView;
import javafx.collections.FXCollections;

import java.net.URL;
import java.util.Date;
import java.util.ResourceBundle;

public class ViewTestsController extends ListViewController<RowView> {

   private static final String RESOURCE = "view-test-row.fxml";

   @Override
   public void initialize(URL location, ResourceBundle resources) {

      data = FXCollections.observableArrayList(
         new RowView(new TestRowController(new Test("307 Midterm 1", new Date(11), new Date(11), "307")), RESOURCE),
         new RowView(new TestRowController(new Test("307 Midterm 2", new Date(11), new Date(11), "307")), RESOURCE)
      );

      super.initialize(location, resources);
   }

}
