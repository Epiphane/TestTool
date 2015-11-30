package com.teampc.controller.question;

import com.teampc.controller.TakeTestController;
import com.teampc.model.admin.*;
import com.teampc.model.test.*;
import com.teampc.model.testtaking.*;
import com.teampc.model.question.*;
import com.teampc.utils.FXUtils;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.Node;
import javafx.scene.layout.Pane;
import javafx.scene.control.*;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.collections.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.net.URL;
import java.time.ZoneId;
import java.util.*;

public abstract class TestSectionController implements Initializable {
   private static final Logger LOG = LoggerFactory.getLogger(TestSectionController.class);

   @FXML
   private ResourceBundle resources;

   @FXML
   private Text prompt;

   protected Question question;
   protected TakeTestController parent;

   /**
    * Initializes the Test Section
    */
   @FXML
   public void initialize(URL fxmlFileLocation, ResourceBundle resources) {
   }

   public void setParent(TakeTestController parent) {
      this.parent = parent;
   }

   public void setQuestion(Question question) {
      this.question = question;

      prompt.setText(question.getPrompt());
   }

   public void onLeave() {

   }
}
