package edu.westga.cs1302.project3.viewmodel;

import edu.westga.cs1302.project3.model.Collection;
import edu.westga.cs1302.project3.model.Comic;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 * ViewModel for the main window of the Comic Collection application.
 * 
 * Manages the list of collections, the name for a new collection, and the
 * comics in the currently selected collection.
 * 
 * @author CS 1302
 * @version Fall 2025
 */
public class MainWindowViewModel {

    private final StringProperty newCollectionName;
    private final ObservableList<Collection> collections;
    private final ObjectProperty<Collection> selectedCollection;

    private final ObservableList<Comic> comicsForSelectedCollection;
    private final ObjectProperty<Comic> selectedComic;

    /**
     * Creates a new MainWindowViewModel.
     * 
     * @precondition none
     * @postcondition newCollectionNameProperty().get().equals("")
     *                && getCollections().isEmpty()
     *                && selectedCollectionProperty().get() == null
     *                && getComicsForSelectedCollection().isEmpty()
     *                && selectedComicProperty().get() == null
     */
    public MainWindowViewModel() {
        this.newCollectionName = new SimpleStringProperty("");
        this.collections = FXCollections.observableArrayList();
        this.selectedCollection = new SimpleObjectProperty<>(null);

        this.comicsForSelectedCollection = FXCollections.observableArrayList();
        this.selectedComic = new SimpleObjectProperty<>(null);
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
     * Gets the observable list of comics for the currently selected collection.
     * 
     * @return the list of comics for the selected collection
     */
    public ObservableList<Comic> getComicsForSelectedCollection() {
        return this.comicsForSelectedCollection;
    }

    /**
     * Gets the property storing the currently selected comic in the selected
     * collection.
     * 
     * @return the selected comic property
     */
    public ObjectProperty<Comic> selectedComicProperty() {
        return this.selectedComic;
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
     *                removed and selectedCollectionProperty().get() == null and
     *                getComicsForSelectedCollection().isEmpty()
     */
    public void removeSelectedCollection() {
        Collection selected = this.selectedCollection.get();
        if (selected != null) {
            this.collections.remove(selected);
            this.selectedCollection.set(null);
            this.comicsForSelectedCollection.clear();
            this.selectedComic.set(null);
        }
    }

    /**
     * Updates the comicsForSelectedCollection list to reflect the comics in the
     * currently selected collection.
     * 
     * @precondition none
     * @postcondition getComicsForSelectedCollection() contains the comics from
     *                the selected collection, or is empty if no collection is
     *                selected
     */
    public void refreshComicsForSelectedCollection() {
        this.comicsForSelectedCollection.clear();
        Collection selected = this.selectedCollection.get();
        if (selected != null) {
            this.comicsForSelectedCollection.addAll(selected.getComics());
        }
        this.selectedComic.set(null);
    }

    /**
     * Adds a new comic to the currently selected collection.
     * 
     * @param title       the title of the comic
     * @param issueNumber the issue number of the comic
     * 
     * @throws IllegalStateException    if no collection is selected
     * @throws IllegalArgumentException if the title or issue number are invalid
     *                                  according to the Comic constructor
     * 
     * @precondition none
     * @postcondition a new Comic with the given title and issue number is added
     *                to the selected collection and appears in
     *                getComicsForSelectedCollection()
     */
    public void addComicToSelectedCollection(String title, int issueNumber) {
        Collection selected = this.selectedCollection.get();
        if (selected == null) {
            throw new IllegalStateException("No collection is selected");
        }

        Comic comic = new Comic(title, issueNumber);
        selected.addComic(comic);
        this.refreshComicsForSelectedCollection();
    }

    /**
     * Removes the currently selected comic from the currently selected collection.
     * 
     * @precondition none
     * @postcondition if a comic was selected and a collection is selected, the
     *                comic is removed from that collection and from
     *                getComicsForSelectedCollection()
     */
    public void removeSelectedComic() {
        Collection selectedCollection = this.selectedCollection.get();
        Comic selectedComic = this.selectedComic.get();

        if (selectedCollection != null && selectedComic != null) {
            selectedCollection.removeComic(selectedComic);
            this.refreshComicsForSelectedCollection();
        }
    }
}
