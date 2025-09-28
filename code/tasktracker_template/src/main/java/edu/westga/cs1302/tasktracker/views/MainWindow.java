package edu.westga.cs1302.tasktracker.views;

import edu.westga.cs1302.tasktracker.model.Task;
import javafx.fxml.FXML;
import javafx.scene.control.*;

public class MainWindow {

    @FXML
    private TextField nameField;

    @FXML
    private TextArea descriptionArea;

    @FXML
    private ComboBox<String> priorityComboBox;

    @FXML
    private Button addTaskButton;

    @FXML
    private ListView<Task> taskListView;

    @FXML
    private TextArea selectedDescriptionArea;

    @FXML
    private TextField selectedPriorityField;

    @FXML
    public void initialize() {
        priorityComboBox.getItems().addAll("Low", "Medium", "High");

        // Listener to display selected task
        taskListView.getSelectionModel().selectedItemProperty().addListener(
            (observable, oldValue, newValue) -> {
                if (newValue != null) {
                    selectedDescriptionArea.setText(newValue.getDescription());
                    selectedPriorityField.setText(newValue.getPriority());
                } else {
                    selectedDescriptionArea.clear();
                    selectedPriorityField.clear();
                }
            }
        );
    }

    @FXML
    private void handleAddTask() {
        String name = nameField.getText();
        String description = descriptionArea.getText();
        String priority = priorityComboBox.getValue();

        if (name == null || name.isEmpty() || priority == null || priority.isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.WARNING, "Name and Priority are required.");
            alert.showAndWait();
            return;
        }

        Task newTask = new Task(name, description, priority);
        taskListView.getItems().add(newTask);

        nameField.clear();
        descriptionArea.clear();
        priorityComboBox.getSelectionModel().clearSelection();
    }

    // 3.C – Update the description of the selected task
    @FXML
    private void handleUpdateDescription() {
        Task selectedTask = taskListView.getSelectionModel().getSelectedItem();
        if (selectedTask != null) {
            String newDescription = selectedDescriptionArea.getText();
            selectedTask.setDescription(newDescription);
            taskListView.refresh();
        } else {
            Alert alert = new Alert(Alert.AlertType.WARNING, "No task selected.");
            alert.showAndWait();
        }
    }
}
