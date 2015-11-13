//package com.teampc.example;
//
//import ch.qos.logback.core.db.dialect.SQLiteDialect;
////import com.teampc.dao.ExamplePersonDAO;
//import javafx.collections.ObservableList;
//import javafx.event.ActionEvent;
//import javafx.fxml.FXML;
//import javafx.fxml.Initializable;
//import javafx.scene.control.TableView;
//import javafx.scene.control.TextField;
//
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//
//import javax.swing.*;
//import java.net.URL;
//import java.util.Collection;
//import java.util.ResourceBundle;
//
///* This controller will be set in an fxml file and operates as the intermediary between the view and the data layer */
///* MVC!!!?!?!? */
//public class ExampleController {
//    private static final Logger log = LoggerFactory.getLogger(ExampleController.class);
//
//    @FXML private TableView<ExamplePerson> tableView; // @FXML annotation allows fxml files to access private and protected data
//    @FXML private TextField firstNameField;
//    @FXML private TextField lastNameField;
//    @FXML private TextField emailField;
//
//    @FXML
//    /* This action is set as a callback in the fxml file */
//    protected void addPerson(ActionEvent event) {
//        if (firstNameField.getText().isEmpty() &&
//                lastNameField.getText().isEmpty()) {
//            return;
//        }
//        ExamplePerson newPerson =
//                new ExamplePerson(0, firstNameField.getText(),
//                        lastNameField.getText(), emailField.getText());
//
//        ExamplePersonDAO.get().insert(newPerson);
//
//        ObservableList<ExamplePerson> data = tableView.getItems();
//        data.add(newPerson);
//
//        firstNameField.setText("");
//        lastNameField.setText("");
//        emailField.setText("");
//    }
//
//    @FXML
//    /* This action is set as a callback in the fxml file */
//    protected void removePerson(ActionEvent event) {
//        ObservableList<ExamplePerson> data = tableView.getItems();
//        ObservableList<ExamplePerson> selectedItems = tableView.getSelectionModel().getSelectedItems();
//
//        ExamplePersonDAO.get().remove(selectedItems);
//        data.removeAll(selectedItems);
//    }
//
//    @FXML
//    private void sayHello() {
//        log.info("Hello!");
//    }
//
//    @FXML
//    /* No-args initialize method will be called by fx */
//    private void initialize() {
//        Collection<ExamplePerson> examplePersons = ExamplePersonDAO.get().fetchAll();
//        ObservableList<ExamplePerson> data = tableView.getItems();
//        data.addAll(examplePersons);
//    }
//}
