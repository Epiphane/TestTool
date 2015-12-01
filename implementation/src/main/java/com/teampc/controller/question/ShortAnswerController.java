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
import javafx.stage.Stage;
import javafx.collections.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.net.URL;
import java.time.ZoneId;
import java.util.*;

public class ShortAnswerController extends TestSectionController {
   private static final Logger LOG = LoggerFactory.getLogger(ShortAnswerController.class);

   @FXML
   private ResourceBundle resources;

   @FXML
   private TextArea response;

   private ShortAnswerQuestionResponse qResponse;

   /**
    * Initializes the Test Section
    */
   @FXML
   public void initialize(URL fxmlFileLocation, ResourceBundle resources) {
   }

   public void setQuestion(QuestionResponse response) {
      super.setQuestion(response);

      qResponse = (ShortAnswerQuestionResponse) response;

      this.response.setText(qResponse.getAnswer());
   }

   public void onLeave() {
      if (qResponse == null) {
         LOG.info("ERROR: No question response set!!");
      }

      qResponse.setAnswer(response.getText());

      LOG.info("Saving short answer response: " + response.getText());
   }
}
