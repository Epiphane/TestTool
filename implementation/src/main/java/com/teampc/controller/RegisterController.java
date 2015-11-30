package com.teampc.controller;

import com.teampc.model.admin.access.UserSession;
import com.teampc.utils.FXUtils;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.stage.Stage;
import lombok.Setter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by Greg on 11/23/15.
 */
public class RegisterController implements Initializable {

   private static final Logger LOG = LoggerFactory.getLogger(RegisterController.class);

   @Setter
   private Stage primaryStage;

   @FXML
   private ChoiceBox<String> accountchoice;

   @FXML
   private TextField userfield;

   @FXML
   private TextField passfield;

   @FXML
   private TextField firstfield;

   @FXML
   private TextField lastfield;

   @FXML
   private Button registerbutton;

   @FXML
   private Label complete;

   @Override
   public void initialize(URL fxmlFileLocation, ResourceBundle resources) {

      accountchoice.setItems(FXCollections.observableArrayList());
      accountchoice.getItems().add("Instructor");
      accountchoice.getItems().add("Student");

   }

   @FXML
   void onRegisterClick(ActionEvent event) throws IOException {
      //FXUtils.switchToScreenAndConfigureController(primaryStage, "login.fxml", RegisterController::setPrimaryStage);
      String username = userfield.getText();
      String password = passfield.getText();
      String first = firstfield.getText();
      String last = lastfield.getText();



      if(!(username.equals("") || password.equals("") || first.equals("") || last.equals(""))) {
         System.out.println(username);
         UserSession.Register(username, password, first, last);
         Stage stage = (Stage) registerbutton.getScene().getWindow();
         stage.close();
      }
      else{
         complete.setVisible(true);
      }
   }

}
