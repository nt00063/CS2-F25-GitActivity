package edu.westga.cs1302.password_generator.view;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;

import edu.westga.cs1302.password_generator.viewmodel.ViewModel;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;

/**
 * Codebehind for the MainWindow of the Application.
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
    @FXML private Label minLengthErrorText;
    @FXML private Button generatePasswordButton;
    @FXML private ListView<String> passwordHistory;

    @FXML private MenuItem saveMenuItem;
    @FXML private MenuItem aboutMenuItem;
    @FXML private MenuItem closeMenuItem;

    private ViewModel vm;

    @FXML
    void initialize() {
        this.vm = new ViewModel();

        this.vm.getRequireDigits().bind(this.mustIncludeDigits.selectedProperty());
        this.vm.getRequireLowercase().bind(this.mustIncludeLowerCaseLetters.selectedProperty());
        this.vm.getRequireUppercase().bind(this.mustIncludeUpperCaseLetters.selectedProperty());
        this.minimumLength.setText(this.vm.getMinimumLength().getValue());
        this.vm.getMinimumLength().bind(this.minimumLength.textProperty());

        this.errorTextLabel.textProperty().bind(this.vm.getErrorText());
        this.passwordHistory.setItems(this.vm.getPasswordHistory());

        this.minimumLength.textProperty().addListener((observable, oldValue, newValue) -> {
            this.minLengthErrorText.setVisible(!newValue.matches("\\d+") || Integer.parseInt(newValue) == 0);
        });
        
        this.minimumLength.textProperty().addListener((obs, oldVal, newVal) -> this.updateGenerateButtonState());
        this.mustIncludeDigits.selectedProperty().addListener((obs, oldVal, newVal) -> this.updateGenerateButtonState());
        this.mustIncludeLowerCaseLetters.selectedProperty().addListener((obs, oldVal, newVal) -> this.updateGenerateButtonState());
        this.mustIncludeUpperCaseLetters.selectedProperty().addListener((obs, oldVal, newVal) -> this.updateGenerateButtonState());

        this.generatePasswordButton.setOnAction((event) -> {
            this.vm.generatePassword();
        });

        this.saveMenuItem.setOnAction(event -> this.handleSave());
        this.aboutMenuItem.setOnAction(event -> this.handleAbout());
        this.closeMenuItem.setOnAction(event -> this.handleClose());
        
        this.updateGenerateButtonState();
    }
    
    private void updateGenerateButtonState() {
        boolean valid = this.vm.isInputValid();
        this.generatePasswordButton.setDisable(!valid);
    }

    /**
     * Handles saving password history to a text file.
     * (Implementation added in Step 3)
     */
    private void handleSave() {
        FileChooser chooser = new FileChooser();
        chooser.setTitle("Save Password History");
        chooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Text Files", "*.txt"));
        File file = chooser.showSaveDialog(this.passwordHistory.getScene().getWindow());
        if (file != null) {
            try (PrintWriter writer = new PrintWriter(file)) {
                for (String password : this.passwordHistory.getItems()) {
                    writer.println(password);
                }
            } catch (IOException error) {
                Alert alert = new Alert(AlertType.ERROR, "Error saving file.");
                alert.showAndWait();
            }
        }
    }
    
    /**
     * Displays an About popup.
     * (Implementation added in Step 3)
     */
    private void handleAbout() {
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("About");
        alert.setHeaderText("Password Generator");
        alert.setContentText("This project generates secure passwords.\nAuthor: Noah Gabriel Toups");
        alert.showAndWait();
    }

    /**
     * Closes the application window.
     * (Implementation added in Step 3)
     */
    private void handleClose() {
        ((Node) this.generatePasswordButton).getScene().getWindow().hide();
    }
}
