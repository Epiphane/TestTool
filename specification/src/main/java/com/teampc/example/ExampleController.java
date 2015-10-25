package com.teampc.example;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/* This controller will be set in an fxml file and operates as the intermediary between the view and the data layer */
/* MVC!!!?!?!? */
public class ExampleController {
    private static final Logger log = LoggerFactory.getLogger(ExampleController.class);

    @FXML private TableView<ExamplePerson> tableView; // @FXML annotation allows fxml files to access private and protected data
    @FXML private TextField firstNameField;
    @FXML private TextField lastNameField;
    @FXML private TextField emailField;

    @FXML
    /* This action is set as a callback in the fxml file */
    protected void addPerson(ActionEvent event) {
        ObservableList<ExamplePerson> data = tableView.getItems();
        data.add(new ExamplePerson(firstNameField.getText(), 
                                   lastNameField.getText(), 
                                   emailField.getText()
                                   ));

        firstNameField.setText("");
        lastNameField.setText("");
        emailField.setText("");
    }

    @FXML
    private void sayHello() {
        log.info("Hello!");
    }

}
