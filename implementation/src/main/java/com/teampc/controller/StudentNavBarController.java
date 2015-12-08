package com.teampc.controller;



import com.teampc.controller.question.QuestionTableController;
import com.teampc.utils.FXUtils;
import com.teampc.utils.FakeDataSrvc;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.stage.Stage;
import lombok.Setter;
import java.io.IOException;

/**
 * StudentNavBarController handles the main navigation bar for students.
 * @author Greg Sawers (gsawers@calpoly.edu)
 */
public class StudentNavBarController {

   @Setter
   private Stage primaryStage;

   @FXML
   private Button fakeDataButton;

   @FXML
   void onTakeTestClick(ActionEvent event) {
      try {
         showLayout("take-test-base.fxml");
      } catch (IOException e) {
         e.printStackTrace();
      }
   }


   @FXML
   void onViewTestsClick(ActionEvent event) {
      try {
         showLayout("studentview-tests-list.fxml");
      } catch (IOException e) {
         e.printStackTrace();
      }

   }

   private void showLayout(String resource) throws IOException {
      FXUtils.newScreenAndConfigureController(resource, (x, y) -> {
      });
   }

   @FXML
   private void addFakeData() {
      FakeDataSrvc.addFakeData();
      new Alert(Alert.AlertType.INFORMATION, "Inserted fake data", ButtonType.CLOSE).show();
   }
}
