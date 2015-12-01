package com.teampc.controller;

import com.teampc.dao.TestDAO;
import com.teampc.model.admin.User;
import com.teampc.model.test.Test;
import com.teampc.utils.FXUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ToggleButton;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by james on 11/9/15.
 */
public class SubmissionRowController implements Initializable{

   private static final Logger LOG = LoggerFactory.getLogger(SubmissionRowController.class);

   @FXML
   private Text studentName;

   private User user;

   public SubmissionRowController(User user) {
      this.user = user;
   }

   @Override
   /** Initializes the row view **/
   public void initialize(URL location, ResourceBundle resources) {
      studentName.setText(user.getDisplayName());
   }
}