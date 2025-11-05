package edu.westga.cs1302.password_generator.view;

import edu.westga.cs1302.password_generator.viewmodel.PasswordViewModel;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.event.ActionEvent;

/**
 * View controller for the Password Generator.
 * Handles bindings and delegates all logic to the ViewModel.
 * 
 * @author CS 1302
 * @version Fall 2025
 */
public class MainWindow {

    @FXML private CheckBox mustIncludeDigits;
    @FXML private CheckBox mustIncludeLowerCaseLetters;
    @FXML private CheckBox mustIncludeUpperCaseLetters;
    @FXML private TextField minimumLength;
    @FXML private TextArea output;

    private PasswordViewModel vm;

    @FXML
    void initialize() {
        this.vm = new PasswordViewModel();

        this.mustIncludeDigits.selectedProperty().bindBidirectional(this.vm.mustIncludeDigitsProperty());
        this.mustIncludeLowerCaseLetters.selectedProperty().bindBidirectional(this.vm.mustIncludeLowerCaseProperty());
        this.mustIncludeUpperCaseLetters.selectedProperty().bindBidirectional(this.vm.mustIncludeUpperCaseProperty());

        this.minimumLength.textProperty().addListener((obs, oldV, newV) -> {
            try {
                int value = Integer.parseInt(newV);
                this.vm.minimumLengthProperty().set(value);
            } catch (NumberFormatException error) {
                this.vm.errorMessageProperty().set("Minimum length must be a positive integer.");
            }
        });

        this.vm.generatedPasswordProperty().addListener((obs, oldV, newV) -> this.output.setText(newV));

        this.vm.errorMessageProperty().addListener((obs, oldV, newV) -> {
            if (newV != null && !newV.isBlank()) {
                Alert alert = new Alert(AlertType.ERROR);
                alert.setContentText(newV);
                alert.show();
            }
        });
    }

    @FXML
    void generatePassword(ActionEvent event) {
        this.vm.generatePassword();
    }
}
