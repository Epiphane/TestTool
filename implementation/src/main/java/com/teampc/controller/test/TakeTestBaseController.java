package com.teampc.controller.test;

import com.google.common.collect.Lists;
import com.teampc.dao.TestDAO;
import com.teampc.model.test.*;
import com.teampc.model.question.*;
import com.teampc.model.testtaking.*;
import com.teampc.utils.FXUtils;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.stage.Stage;
import javafx.collections.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.*;

/**
 * TakeTestBaseController is the list of available tests, and allows a student to select
 * a test and begin work on it.
 *
 * @author tsteinke
 */
public class TakeTestBaseController implements Initializable {
   private static final Logger LOG = LoggerFactory.getLogger(TakeTestBaseController.class);

   @FXML
   private ResourceBundle resources;

   @FXML
   private URL location;

   @FXML
   private ListView<Test> availableTests;

   @FXML
   private MenuButton testSelector;

   private Test currentSelection;

   /**
    * Initializes the Create Test Options UI with values for the selection lists, spinner, and input boxes
    */
   @FXML
   public void initialize(URL fxmlFileLocation, ResourceBundle resources) {
      initTestList();
   }

   private void selectTest(Test selection) {
      currentSelection = selection;
      testSelector.setText(selection.toString());
   }

   private void initTestList() {
      ObservableList<Test> tests = FXCollections.observableArrayList(TestDAO.getInstance().fetchAll());

      Collection<MenuItem> testData = new ArrayList<>();
      tests.forEach(test -> {
         MenuItem menuItem = new MenuItem(test.toString());

         testData.add(menuItem);
         menuItem.setOnAction(event -> selectTest(test));
      });

      availableTests.setItems(tests);
      testSelector.getItems().addAll(testData);

      selectTest(tests.get(0));
   }

   /**
    * Handler for the take test button
    */
   @FXML
   void onTakeTest(ActionEvent event) throws IOException {
      LOG.info("Take test! " + currentSelection.toString());

      if (currentSelection.isOpen()) {
         FXUtils.switchToScreenAndConfigureController((Stage) ((Node) event.getSource()).getScene().getWindow(), "take-test.fxml",
            (controller, stage) -> {
               try {
                  ((TakeTestController) controller).setTest(currentSelection);
               }
               catch (Exception e) {
                  LOG.info("ERROR");
                  LOG.info(e.getMessage());
               }
            });
      }
      else {
         LOG.info("Cannot take test: it is not open!");
      }
   }
}
