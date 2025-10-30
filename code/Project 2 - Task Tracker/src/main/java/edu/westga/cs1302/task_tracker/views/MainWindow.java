package edu.westga.cs1302.task_tracker.views;

import java.util.Comparator;
import java.util.List;

import edu.westga.cs1302.task_tracker.model.Ascending;
import edu.westga.cs1302.task_tracker.model.Descending;
import edu.westga.cs1302.task_tracker.model.NameAscending;
import edu.westga.cs1302.task_tracker.model.NameDescending;
import edu.westga.cs1302.task_tracker.model.Task;
import edu.westga.cs1302.task_tracker.model.Task.TaskPriority;
import edu.westga.cs1302.task_tracker.model.TaskUtility;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

/** Controller class for MainWindow of the Task Tracker system.
 * 
 * @author CS 1302
 * @version Fall 2025
 */
public class MainWindow {
    @FXML private TextArea description;
    @FXML private Label highCount;
    @FXML private Label lowCount;
    @FXML private Label mediumCount;
    @FXML private TextField name;
    @FXML private ComboBox<TaskPriority> priority;
    @FXML private TextArea selectedDescription;
    @FXML private TextField selectedPriority;
    @FXML private ListView<Task> tasks;
    @FXML private ComboBox<Comparator<Task>> order;
    
    // Step 3A fields (kept even if FXML isn’t connected yet)
    @FXML private ListView<Task> subtasks;
    @FXML private Button addSubtask;

    /** Add a new task with the provided information to the listview.
     * 
     * @precondition none
     * @postcondition A task will be added to the listview with 
     * 							  1) a name matching the text of the name textfield, 
     * 							  2) a description matching the text of the description textarea,
     * 							  3) a priority matching the selected value of the priority combobox,
     * 
     * @param event we will not use this parameter, only here due to JavaFX Library requirement
     */
    @FXML 
    void addTask(ActionEvent event) {
    	try {
    		this.tasks.getItems().add(new Task(this.name.getText(), this.description.getText(), this.priority.getValue()));
    	} catch (IllegalArgumentException error) {
    		Alert alert = new Alert(AlertType.ERROR);
    		alert.setContentText(error.getMessage());
    		alert.showAndWait();
    	}
    }

    /** Display the priority and description of the task selected in the listview.
     * 
     * @precondition none
     * @postcondition the description for the selected task will be displayed in the selectedDescription text area &&
     * 				  the priority for the selected task will be displayed in the selectedPriority text field
     * 
     * @param event we will not use this parameter, only here due to JavaFX Library requirement
     */
    @FXML
    void selectTask(MouseEvent event) {
    	Task selectedTask = this.tasks.getSelectionModel().getSelectedItem();
    	if (selectedTask != null) {
    		this.selectedPriority.setText(selectedTask.getPriority().toString());
    		this.selectedDescription.setText(selectedTask.getDescription());

    		// Step 3C: display subtasks (if any)
    		if (this.subtasks != null) {
    			this.subtasks.getItems().clear();
    			List<Task> subs = selectedTask.getSubTasks();
    			this.subtasks.getItems().addAll(subs);
    		}
    	}
    }

    /** Remove the currently selected task.
     * 
     * @precondition none
     * @postcondition task selected in the listview will be removed
     * 
     * @param event we will not use this parameter, only here due to JavaFX Library requirement
     */
    @FXML
    void removeTask(ActionEvent event) {
    	Task selectedTask = this.tasks.getSelectionModel().getSelectedItem();
    	if (selectedTask != null) {
    		this.tasks.getItems().remove(selectedTask);
    	}
    }

    /** Update the description of the selected task.
     * 
     * @precondition none
     * @postcondition description for the task selected in the listview will be updated to match the text in the selectedDescription text area.
     * 
     * @param event we will not use this parameter, only here due to JavaFX Library requirement
     */
    @FXML
    void updateDescription(ActionEvent event) {
    	Task selectedTask = this.tasks.getSelectionModel().getSelectedItem();
    	if (selectedTask != null) {
    		selectedTask.setDescription(this.selectedDescription.getText());
    	}
    }

    /** Display the count of tasks for each priority.
     * 
     * @precondition none
     * @postcondition count of tasks for each priority are displayed in the appropriate labels.
     * 
     * @param event we will not use this parameter, only here due to JavaFX Library requirement
     */
    @FXML
    void countPriorities(ActionEvent event) {
    	this.highCount.setText(Integer.toString(TaskUtility.countOfPriority(TaskPriority.HIGH, this.tasks.getItems())));
    	this.mediumCount.setText(Integer.toString(TaskUtility.countOfPriority(TaskPriority.MEDIUM, this.tasks.getItems())));
    	this.lowCount.setText(Integer.toString(TaskUtility.countOfPriority(TaskPriority.LOW, this.tasks.getItems())));
    }
    
    /** Sort tasks based on the selected ordering.
     * 
     * @precondition none
     * @postcondition tasks in the listview are sorted based on the provided ordering.
     * 
     * @param event we will not use this parameter, only here due to JavaFX Library requirement
     */
    @FXML
    void sortTasks(ActionEvent event) {
    	if (this.order.getValue() != null) {
    		this.tasks.getItems().sort(this.order.getValue());
    	}
    }

    /** Add a subtask to the currently selected task.
     * 
     * @precondition a task must be selected in the main list
     * @postcondition the selected task is replaced with a ContainerTask that includes the new subtask
     * 
     * @param event not used
     */
    @FXML
    void addSubtask(ActionEvent event) {
    	Task parentTask = this.tasks.getSelectionModel().getSelectedItem();
    	if (parentTask == null) {
    		Alert alert = new Alert(AlertType.ERROR);
    		alert.setContentText("Please select a task before adding a subtask.");
    		alert.showAndWait();
    		return;
    	}

    	try {
    		Task sub = new Task(this.name.getText(), this.description.getText(), this.priority.getValue());
    		Task updatedParent = parentTask.addTask(sub);

    		int index = this.tasks.getSelectionModel().getSelectedIndex();
    		this.tasks.getItems().set(index, updatedParent);

    		this.tasks.getSelectionModel().select(updatedParent);
    		this.selectTask(null);
    	} catch (IllegalArgumentException error) {
    		Alert alert = new Alert(AlertType.ERROR);
    		alert.setContentText(error.getMessage());
    		alert.showAndWait();
    	}
    }

    /** Display the selected subtask’s details in an alert popup.
     * 
     * @precondition a subtask must be selected in the subtasks list
     * @postcondition a popup alert shows the name, description, and priority of the selected subtask
     * 
     * @param event we will not use this parameter, only here due to JavaFX Library requirement
     */
    @FXML
    void selectSubtask(MouseEvent event) {
    	if (this.subtasks == null) {
    		return;
    	}
    	Task sub = this.subtasks.getSelectionModel().getSelectedItem();
    	if (sub != null) {
    		Alert alert = new Alert(AlertType.INFORMATION);
    		alert.setTitle("Subtask Details");
    		alert.setHeaderText(sub.getName());
    		alert.setContentText("Priority: " + sub.getPriority() + "\n\nDescription:\n" + sub.getDescription());
    		alert.showAndWait();
    	}
    }

    /** Perform any needed initialization of UI components and underlying objects.
     * 
     * @precondition none
     * @postcondition none
     */
    @FXML
    public void initialize() {
        this.priority.getItems().addAll(TaskPriority.HIGH, TaskPriority.MEDIUM, TaskPriority.LOW);
        this.priority.setValue(this.priority.getItems().get(0));

        this.order.getItems().add(new Ascending());
        this.order.getItems().add(new Descending());
        this.order.getItems().add(new NameAscending());
        this.order.getItems().add(new NameDescending());

        this.priority.setValue(this.priority.getItems().get(0));
    }

}
