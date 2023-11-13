package com.example.project3;

import javafx.collections.FXCollections;
import javafx.collections.ObservableArray;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class MovieListerController implements Initializable {

    @FXML
    private ListView<String> listBox;
    @FXML
    private TextField inputBox;
    @FXML
    private Button addButton;
    @FXML
    private Button removeButton;
    @FXML
    private Button loadButton;
    @FXML
    private Button saveButton;

    //Actual list data
    private ObservableList<String> listItems;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        listItems = FXCollections.observableArrayList();
        listBox.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        listBox.setItems(listItems);
    }

    @FXML
    private void handleAddEvent(ActionEvent event)
    {
        Alert alertBox = new Alert(Alert.AlertType.INFORMATION);
        alertBox.setHeaderText("You already added that same movie");
        for (String each : listItems) {
            if (each.equalsIgnoreCase(inputBox.getText())) {
                alertBox.showAndWait();
                return;
            }
        }
        listItems.add(inputBox.getText().trim().toLowerCase());
        inputBox.setText("");
        inputBox.requestFocus();
    }

    @FXML
    private void handleRemoveEvent(ActionEvent event)
    {
        ObservableList<String> clickedItems = listBox.getSelectionModel().getSelectedItems();
        Alert alertBox = new Alert(Alert.AlertType.INFORMATION);
        alertBox.setHeaderText("Please select an item to delete!");
        if (clickedItems.size() == 0) {
            alertBox.showAndWait();
            return;
        }

        listItems.removeAll(clickedItems);
    }

    @FXML
    private void handleLoadEvent(ActionEvent event)
    {
        listItems.addAll(MovieListFile.read());
    }

    @FXML
    private void handleSaveEvent(ActionEvent event)
    {
        MovieListFile fileCreator = MovieListFile.create().using(listItems);
    }
}