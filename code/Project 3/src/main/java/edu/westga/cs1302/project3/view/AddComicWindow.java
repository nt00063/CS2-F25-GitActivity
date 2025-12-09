package edu.westga.cs1302.project3.view;

import edu.westga.cs1302.project3.viewmodel.AddComicViewModel;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * Controller for the AddComicWindow.
 * 
 * Handles user input for creating a new Comic.
 * 
 * @author CS
 * @version Fall 2025
 */
public class AddComicWindow {

    @FXML
    private TextField titleTextField;

    @FXML
    private TextField issueNumberTextField;

    @FXML
    private Button confirmButton;

    @FXML
    private Button cancelButton;

    private AddComicViewModel viewModel;

    /**
     * Sets the ViewModel used by this window.
     * 
     * @param vm the AddComicViewModel
     */
    public void setViewModel(AddComicViewModel vm) {
        this.viewModel = vm;

        this.titleTextField.textProperty().bindBidirectional(vm.titleProperty());
        this.issueNumberTextField.textProperty().bindBidirectional(vm.issueNumberProperty());
    }

    @FXML
    private void handleConfirm() {
        try {
            this.viewModel.addComic();
            ((Stage) this.confirmButton.getScene().getWindow()).close();
        } catch (IllegalArgumentException | IllegalStateException error) {

        }
    }

    @FXML
    private void handleCancel() {
        ((Stage) this.cancelButton.getScene().getWindow()).close();
    }
}
