package edu.westga.cs1302.project3.view;

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
    private ListView<String> collectionsListView;

    @FXML
    private Button removeCollectionButton;

    @FXML
    private ContextMenu collectionsContextMenu;

    @FXML
    private MenuItem removeCollectionMenuItem;

    /**
     * Initializes the main window.
     * 
     * @precondition none
     * @postcondition none
     */
    @FXML
    private void initialize() {
        // ViewModel wiring, bindings, and event handlers will be added later.
    }
}

