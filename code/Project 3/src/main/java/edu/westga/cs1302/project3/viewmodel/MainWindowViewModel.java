package edu.westga.cs1302.project3.viewmodel;

import edu.westga.cs1302.project3.model.Collection;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 * ViewModel for the main window of the Comic Collection application.
 * 
 * Manages the list of collections and the name for a new collection.
 * 
 * @author CS 1302
 * @version Fall 2025
 */
public class MainWindowViewModel {

    private final StringProperty newCollectionName;
    private final ObservableList<Collection> collections;
    private final ObjectProperty<Collection> selectedCollection;

    /**
     * Creates a new MainWindowViewModel.
     * 
     * @precondition none
     * @postcondition newCollectionNameProperty().get().equals("")
     *               && getCollections().isEmpty()
     *               && selectedCollectionProperty().get() == null
     */
    public MainWindowViewModel() {
        this.newCollectionName = new SimpleStringProperty("");
        this.collections = FXCollections.observableArrayList();
        this.selectedCollection = new SimpleObjectProperty<>(null);
    }

    /**
     * Gets the property storing the name for a new collection.
     * 
     * @return the new collection name property
     */
    public StringProperty newCollectionNameProperty() {
        return this.newCollectionName;
    }

    /**
     * Gets the observable list of collections.
     * 
     * @return the list of collections
     */
    public ObservableList<Collection> getCollections() {
        return this.collections;
    }

    /**
     * Gets the property storing the currently selected collection.
     * 
     * @return the selected collection property
     */
    public ObjectProperty<Collection> selectedCollectionProperty() {
        return this.selectedCollection;
    }

    /**
     * Adds a new collection using the current value of the newCollectionName
     * property.
     * 
     * @throws IllegalArgumentException if the name is null or blank
     * 
     * @precondition none
     * @postcondition getCollections().contains(new Collection(name.trim()))
     *                && newCollectionNameProperty().get().equals("")
     */
    public void addCollection() {
        String name = this.newCollectionName.get();
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("Collection name cannot be null or blank");
        }

        Collection collection = new Collection(name);
        this.collections.add(collection);
        this.newCollectionName.set("");
    }

    /**
     * Removes the currently selected collection from the list of collections (if
     * one is selected).
     * 
     * @precondition none
     * @postcondition if selectedCollectionProperty().get() was in the list, it is
     *                removed and selectedCollectionProperty().get() == null
     */
    public void removeSelectedCollection() {
        Collection selected = this.selectedCollection.get();
        if (selected != null) {
            this.collections.remove(selected);
            this.selectedCollection.set(null);
        }
    }
}
