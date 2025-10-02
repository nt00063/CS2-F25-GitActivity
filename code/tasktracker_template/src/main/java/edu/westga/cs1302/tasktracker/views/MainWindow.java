package edu.westga.cs1302.tasktracker.views;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

/**
 * Controller for the main window of Task Tracker application.
 * Handles user input and updates the task list.
 * 
 * @author NoahG
 * @version Fall 2025
 */
public class MainWindow {

    @FXML
    private TextField taskNameField;

    @FXML
    private TextArea taskDescriptionArea;

    @FXML
    private ComboBox<Integer> priorityComboBox;

    @FXML
    private Button addTaskButton;

    @FXML
    private ListView<String> taskListView;

    /**
     * Initializes the controller.
     */
    @FXML
    public void initialize() {
        // Initialize combo box with priorities
        this.priorityComboBox.getItems().addAll(1, 2, 3, 4, 5);
    }

    /**
     * Handles the Add Task button click.
     */
    @FXML
    public void handleAddTask() {
        String name = this.taskNameField.getText();
        String description = this.taskDescriptionArea.getText();
        Integer priority = this.priorityComboBox.getValue();

        if (name == null || name.isBlank() || priority == null) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Input Error");
            alert.setHeaderText("Missing Task Information");
            alert.setContentText("Please provide a name and priority for the task.");
            alert.showAndWait();
            return;
        }

        String taskEntry = String.format("%s - %s (Priority %d)", name, description, priority);
        this.taskListView.getItems().add(taskEntry);

        this.taskNameField.clear();
        this.taskDescriptionArea.clear();
        this.priorityComboBox.setValue(null);
    }
}
