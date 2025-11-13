package edu.westga.cs1302.password_generator.view;

import edu.westga.cs1302.password_generator.viewmodel.ViewModel;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

/**
 * Codebehind for the MainWindow of the Application.
 * 
 * Updated to include:
 * - Regex input validation for minimum length
 * - Button disabling when input invalid
 * - ListView binding to display password history
 * 
 * @author CS 1302
 * @version Fall 2025
 */
public class MainWindow {

    @FXML private CheckBox mustIncludeDigits;
    @FXML private CheckBox mustIncludeLowerCaseLetters;
    @FXML private CheckBox mustIncludeUpperCaseLetters;
    @FXML private TextField minimumLength;
    @FXML private Label errorTextLabel;
    @FXML private Button generatePasswordButton;

    /** ListView that displays all previously generated passwords. */
    @FXML private ListView<String> passwordList;

    private ViewModel vm;

    /**
     * Initializes the UI bindings and event handlers.
     */
    @FXML
    void initialize() {
        this.vm = new ViewModel();

        this.vm.getRequireDigits().bind(this.mustIncludeDigits.selectedProperty());
        this.vm.getRequireLowercase().bind(this.mustIncludeLowerCaseLetters.selectedProperty());
        this.vm.getRequireUppercase().bind(this.mustIncludeUpperCaseLetters.selectedProperty());

        this.minimumLength.setText(this.vm.getMinimumLength().get());

        /**
         * NEW: Regex validation listener for minimum length.
         * Disables button and shows error when invalid.
         */
        this.minimumLength.textProperty().addListener((obs, oldValue, newValue) -> {
            if (!newValue.matches("^[1-9][0-9]*$")) {
                this.errorTextLabel.setText("Minimum length must be a positive integer");
                this.generatePasswordButton.setDisable(true);
            } else {
                this.errorTextLabel.setText("");
                this.generatePasswordButton.setDisable(false);
                this.vm.getMinimumLength().set(newValue);
            }
        });

        this.errorTextLabel.textProperty().bind(this.vm.getErrorText());

        this.passwordList.setItems(this.vm.passwordListProperty());

        this.generatePasswordButton.setOnAction(event -> {
            this.vm.generatePassword();
        });
    }
}
