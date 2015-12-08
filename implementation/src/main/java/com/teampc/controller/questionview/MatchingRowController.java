package com.teampc.controller.questionview;

import com.sun.glass.ui.View;
import com.teampc.controller.ViewSubmissionsController;
import com.teampc.dao.TestDAO;
import com.teampc.model.test.Test;
import com.teampc.utils.FXUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.*;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.stream.Collectors;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import com.teampc.utils.FXUtils;

import java.io.*;
import java.util.*;

/**
 * Created by james on 11/9/15.
 */
public class MatchingRowController implements Initializable{

   private static final Logger LOG = LoggerFactory.getLogger(MatchingRowController.class);

   @FXML
   private Text keyDisplay;

   @FXML
   private MenuButton values;

   private String key, value;
   private List<String> options;

   public MatchingRowController(String key, List<String> options, String value) {
      this.key = key;
      this.value = value;

      this.options = options;
   }

   public MatchingRowController(String key, List<String> options) {
      this(key, options, "");
   }

   @Override
   /** Initializes the row view **/
   public void initialize(URL location, ResourceBundle resources) {
      keyDisplay.setText(key);

      // Add menu items
      values.getItems().addAll(options.stream().map(option -> {
         MenuItem menuItem = new MenuItem(option);

         menuItem.setOnAction(event -> values.setText(option));
         return menuItem;
      }).collect(Collectors.toList()));

      values.setText(value);
   }

   public void putInMap(Map<String, String> map) {
      map.put(key, values.getText());
   }
}
