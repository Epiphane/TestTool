package com.teampc.controller;

import com.google.common.annotations.VisibleForTesting;
import com.teampc.controller.test.TestRowController;
import com.teampc.dao.TestDAO;
import com.teampc.model.test.Test;
import com.teampc.view.RowView;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

/**
 * The view tests controller provides methods that allow the model and views to interact.
 * View changes when creating a test row will be persistent in the model through this class.
 * Changes to a test on the model will be displayed in the view through the use of
 * methods in this class.
 *
 * The UI is a list of tests.
 *
 * @author Jameson Li (jrli@calpoly.edu)
 */
public class ViewTestsController extends ListViewController<RowView<TestRowController>> {

   /** Resource name of the fxml file **/
   private static final String RESOURCE = "view-test-row.fxml";

   @Override
   /**
    * Initializes the tests list view with tests from the TestDAO
    *
    pre: data == null
    *
    post: data' != null &&
            testList.size() == data.size()
    */
   public void initialize(URL location, ResourceBundle resources) {

      data = FXCollections.observableArrayList();

      List<Test> testList = TestDAO.getInstance().fetchAll();
      testList.forEach(test -> data.add(new RowView<>(new TestRowController(test), RESOURCE)));

      initView();
   }

   /**
    * Simulates controller initialization
    */
   @VisibleForTesting
   public ObservableList<RowView<TestRowController>> testMe() {
      initialize(null, null);
      return data;
   }

}
