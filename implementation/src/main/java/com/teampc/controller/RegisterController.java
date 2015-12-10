package com.teampc.controller;

import com.teampc.model.admin.User;
import com.teampc.model.admin.access.UserSession;
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
 * RegisterController handles all events that happen on the registration page.
 * Clicking the register button after all information is filled in registers the user with the application.
 * @author Greg Sawers gsawers (gsawers@calpoly.edu)
 */
public class RegisterController implements Initializable {

   private static final Logger LOG = LoggerFactory.getLogger(RegisterController.class);

   @Setter
   private Stage primaryStage;

   /**
    * Corresponds to the choice box for the type of user account.
    */
   @FXML
   private ChoiceBox<User.UserType> accountchoice;

   /**
    * Corresponds to the username text field.
    */
   @FXML
   private TextField userfield;

   /**
    * Corresponds to the password text field.
    */
   @FXML
   private TextField passfield;

   /**
    * Corresponds to the first name text field.
    */
   @FXML
   private TextField firstfield;

   /**
    * Corresponds to the last name text field.
    */
   @FXML
   private TextField lastfield;

   /**
    * Corresponds to the register user button.
    */
   @FXML
   private Button registerbutton;

   /**
    * Corresponds to the prompt to let users know they have not put in enough information
    */
   @FXML
   private Label complete;


   /**
    *  Initialize the screen
    * @param fxmlFileLocation
    * @param resources
    */
   @Override
   public void initialize(URL fxmlFileLocation, ResourceBundle resources) {
      accountchoice.setItems(FXCollections.observableArrayList(User.UserType.values()));
   }

   /**
    * Handles when a user clicks the register button on the registration screen.
    * @param event Clicking the button
    * @throws IOException
    */
   @FXML
   public void onRegisterClick(ActionEvent event) throws IOException {
      //FXUtils.switchToScreenAndConfigureController(primaryStage, "login.fxml", RegisterController::setPrimaryStage);
      /**
       * Grab the username from the view.
       */
      String username = userfield.getText();
      /**
       * Grab the password from the view.
       */
      String password = passfield.getText();
      /**
       * Grab the first name from the view.
       */
      String first = firstfield.getText();
      /**
       * Grab the last name from the view.
       */
      String last = lastfield.getText();
      /**
       * Grab the account type
       */
      User.UserType type = accountchoice.getValue();

      /**
       * If all of the necessary fields are filled out, register the user.
       */

      if(!UserSession.register(username, password, first, last, type)) {
         LOG.debug(username);
         Stage stage = (Stage) registerbutton.getScene().getWindow();
         stage.close();
      }
      /**
       * Otherwise prompt the user to fill in all fields.
       */
      else{
         complete.setVisible(true);
      }
   }

}
