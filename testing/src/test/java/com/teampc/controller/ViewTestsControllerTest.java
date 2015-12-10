package com.teampc.controller;

import com.teampc.controller.test.TestRowController;
import com.teampc.dao.TestDAO;
import com.teampc.view.RowView;
import javafx.collections.ObservableList;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertTrue;

public class ViewTestsControllerTest {

   @Test
   public void initTest() {
      ViewTestsController testsController = new ViewTestsController();

      ObservableList<RowView<TestRowController>> observableList = testsController.testMe();
      List<com.teampc.model.test.Test> testList = TestDAO.getInstance().fetchAll();

      assertTrue(observableList.size() <= testList.size());
   }

}
