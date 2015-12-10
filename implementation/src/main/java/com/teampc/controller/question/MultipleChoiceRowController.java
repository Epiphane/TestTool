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
 * Controller for a multiple choice question row layout
 */
@Getter
public class MultipleChoiceRowController {

   /** Horizontal box encapsulating row widgets */
   @FXML
   private HBox box;

   /** Selected button to indicate answer is correct */
   @FXML
   private RadioButton selected;

   /** Label displaying answer text */
   @FXML
   private Label text;

   /** Function for deleting row from parent context */
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
