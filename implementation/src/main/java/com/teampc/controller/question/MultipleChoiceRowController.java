package com.teampc.controller.question;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.layout.HBox;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

import java.util.function.Consumer;

/**
 * Created by adufrene on 12/6/15.
 *
 */
@Getter
public class MultipleChoiceRowController {

   @FXML
   private HBox box;

   @FXML
   private RadioButton selected;

   @FXML
   private Label text;

   @Setter
   @Getter(AccessLevel.NONE)
   private Consumer<String> parentDelete;

   /**
    * Delete this row from the list of choices
    */
   @FXML
   private void deleteRow() {
      parentDelete.accept(text.getText());
   }
}
