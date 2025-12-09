package edu.westga.cs1302.project3.view;

import java.io.IOException;

import edu.westga.cs1302.project3.model.Collection;
import edu.westga.cs1302.project3.model.Comic;
import edu.westga.cs1302.project3.viewmodel.AddComicViewModel;
import edu.westga.cs1302.project3.viewmodel.MainWindowViewModel;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.ListView;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
import javafx.stage.Modality;
import javafx.stage.Stage;

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

    @FXML
    private ListView<Comic> comicsListView;

    @FXML
    private Button addComicButton;

    @FXML
    private Button removeComicButton;

    @FXML
    private MenuItem removeComicMenuItem;

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

        this.newCollectionNameTextField.textProperty()
            .bindBidirectional(this.viewModel.newCollectionNameProperty());

        this.addCollectionButton.disableProperty().bind(
            this.viewModel.newCollectionNameProperty().isEmpty()
        );

        this.collectionsListView.setItems(this.viewModel.getCollections());

        this.collectionsListView.getSelectionModel().selectedItemProperty()
            .addListener((observable, oldValue, newValue) -> {
                this.viewModel.selectedCollectionProperty().set(newValue);
                this.viewModel.refreshComicsForSelectedCollection();
            });

        this.comicsListView.setItems(this.viewModel.getComicsForSelectedCollection());

        this.comicsListView.getSelectionModel().selectedItemProperty()
            .addListener((observable, oldComic, newComic) -> {
                this.viewModel.selectedComicProperty().set(newComic);
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
        this.comicsListView.getSelectionModel().clearSelection();
    }

    /**
     * Handles the request to open the Add Comic window.
     * 
     * @precondition none
     * @postcondition if a collection is selected and input is valid, a new comic
     *                is added to that collection
     */
    @FXML
    private void handleOpenAddComicWindow() {
        // Do nothing if no collection is selected
        if (this.viewModel.selectedCollectionProperty().get() == null) {
            return;
        }

        try {
            FXMLLoader loader = new FXMLLoader(
                this.getClass().getResource("AddComicWindow.fxml")
            );
            Stage dialogStage = new Stage();
            dialogStage.initOwner(this.addComicButton.getScene().getWindow());
            dialogStage.initModality(Modality.APPLICATION_MODAL);
            dialogStage.setTitle("Add Comic");

            Scene scene = new Scene(loader.load());
            dialogStage.setScene(scene);

            AddComicWindow controller = loader.getController();
            AddComicViewModel addComicViewModel = new AddComicViewModel(this.viewModel);
            controller.setViewModel(addComicViewModel);

            dialogStage.showAndWait();

        } catch (IOException exception) {
            exception.printStackTrace();
        }
    }

    /**
     * Handles the request to remove the selected comic.
     * 
     * @precondition none
     * @postcondition if a comic was selected, it is removed from the selected
     *                collection and the list is updated
     */
    @FXML
    private void handleRemoveComic() {
        this.viewModel.removeSelectedComic();
        this.comicsListView.getSelectionModel().clearSelection();
    }
}
