package edu.westga.cs1302.project3.view;

import edu.westga.cs1302.project3.model.Collection;
import edu.westga.cs1302.project3.viewmodel.MainWindowViewModel;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.ListView;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;

/**
 * Codebehind for the main window of the Comic Collection application.
 * 
 * @author CS 1302
 * @version Fall 2025
 */
public class MainWindow {

    @FXML
    private TextField newCollectionNameTextField;

    @FXML
    private Button addCollectionButton;

    @FXML
    private ListView<Collection> collectionsListView;

    @FXML
    private Button removeCollectionButton;

    @FXML
    private ContextMenu collectionsContextMenu;

    @FXML
    private MenuItem removeCollectionMenuItem;

    private MainWindowViewModel viewModel;

    /**
     * Initializes the main window.
     * 
     * @precondition none
     * @postcondition none
     */
    @FXML
    private void initialize() {
        this.viewModel = new MainWindowViewModel();

        // Bind TextField <-> ViewModel newCollectionName
        this.newCollectionNameTextField.textProperty()
            .bindBidirectional(this.viewModel.newCollectionNameProperty());

        // Disable add button when name is empty
        this.addCollectionButton.disableProperty().bind(
            this.viewModel.newCollectionNameProperty().isEmpty()
        );

        // Bind ListView items to the collections list
        this.collectionsListView.setItems(this.viewModel.getCollections());

        // Keep ViewModel's selectedCollection in sync with ListView selection
        this.collectionsListView.getSelectionModel().selectedItemProperty()
            .addListener((observable, oldValue, newValue) -> {
                this.viewModel.selectedCollectionProperty().set(newValue);
            });
    }

    /**
     * Handles the request to add a new collection.
     * 
     * @precondition none
     * @postcondition if newCollectionName is valid, a new collection is added
     *                and the name field is cleared
     */
    @FXML
    private void handleAddCollection() {
        try {
            this.viewModel.addCollection();

        } catch (IllegalArgumentException exception) {

        }
    }

    /**
     * Handles the request to remove the selected collection.
     * 
     * @precondition none
     * @postcondition if a collection was selected, it is removed and selection is cleared
     */
    @FXML
    private void handleRemoveCollection() {
        this.viewModel.removeSelectedCollection();
        this.collectionsListView.getSelectionModel().clearSelection();
    }
}
