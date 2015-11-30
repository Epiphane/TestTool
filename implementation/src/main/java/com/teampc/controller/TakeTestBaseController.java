package com.teampc.controller;

import com.teampc.model.test.*;
import com.teampc.model.question.*;
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
import java.net.URL;
import java.time.ZoneId;
import java.util.*;

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
      // TODO Load tests from DAO
      Date userStartDate = new GregorianCalendar(2015, 10, 11).getTime();
      Date userEndDate = new GregorianCalendar(2015, 12, 12).getTime();
      Test testA = new Test("Final", userStartDate, userEndDate, "CPE 101");
      
      // Add fake questions to test A...
      List<Question> questions = testA.getQuestions();
      Question question = new ShortAnswerQuestion();
      question.setPrompt("What is your name?");
      questions.add(question);
      question = new ShortAnswerQuestion();
      question.setPrompt("What is your number?");
      questions.add(question);

      testA.publish();

      userStartDate = new GregorianCalendar(2015, 9, 30).getTime();
      userEndDate = new GregorianCalendar(2015, 9, 31).getTime();
      Test testB = new Test("SVN Basics", userStartDate, userEndDate, "CPE 307");
      testB.publish();

      userStartDate = new GregorianCalendar(2015, 12, 10).getTime();
      userEndDate = new GregorianCalendar(2015, 12, 11).getTime();
      Test testC = new Test("Final", userStartDate, userEndDate, "CPE 307");
      testC.publish();

      ObservableList<Test> tests = FXCollections.observableArrayList(testA, testB, testC);

      Collection<MenuItem> testData = new ArrayList<>();
      tests.forEach(test -> {
         MenuItem menuItem = new MenuItem(test.toString());

         testData.add(menuItem);
         menuItem.setOnAction(event -> selectTest(test));
      });

      availableTests.setItems(tests);
      testSelector.getItems().addAll(testData);

      selectTest(testB);
   }

   /**
    * Handler for the create test button
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
      // if (Strings.isNullOrEmpty(testName.getText()) || Strings.isNullOrEmpty(courseType.getText())) {
      //    LOG.info("missing test name, courseType, ...");
      //    return;
      // }

      // Date userStartDate = null;
      // Date userEndDate = null;

      // if (startDate.getValue() != null) { userStartDate = TestUtils.localDateToDate(startDate.getValue()); }
      // if (endDate.getValue() != null) { userEndDate = TestUtils.localDateToDate(endDate.getValue()); }

      // Test newTest = new Test(testName.getText(), userStartDate, userEndDate, courseType.getText());
      // TestDAO.getInstance().insert(newTest);
      // LOG.info("new test submitted");

      // // TODO: remove this and change to the generate questions view
      // Node source = (Node) event.getSource();
      // Stage stage = (Stage) source.getScene().getWindow();
      // stage.close();
   }
}
