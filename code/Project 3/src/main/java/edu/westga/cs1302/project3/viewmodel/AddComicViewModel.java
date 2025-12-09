package edu.westga.cs1302.project3.viewmodel;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 * ViewModel for the AddComicWindow.
 * 
 * Stores the title and issue number for a new comic and delegates the
 * add operation to the MainWindowViewModel.
 * 
 * @author CS
 * @version Fall 2025
 */
public class AddComicViewModel {

    private final StringProperty title;
    private final StringProperty issueNumberText;
    private final MainWindowViewModel mainWindowViewModel;

    /**
     * Creates a new AddComicViewModel.
     * 
     * @param mainWindowViewModel the main window ViewModel used to add comics
     * 
     * @precondition mainWindowViewModel != null
     * @postcondition titleProperty().get().equals("")
     *                && issueNumberProperty().get().equals("")
     */
    public AddComicViewModel(MainWindowViewModel mainWindowViewModel) {
        if (mainWindowViewModel == null) {
            throw new IllegalArgumentException("MainWindowViewModel cannot be null");
        }
        this.mainWindowViewModel = mainWindowViewModel;
        this.title = new SimpleStringProperty("");
        this.issueNumberText = new SimpleStringProperty("");
    }

    /**
     * Gets the property storing the title for the new comic.
     * 
     * @return the title property
     */
    public StringProperty titleProperty() {
        return this.title;
    }

    /**
     * Gets the property storing the issue number for the new comic as text.
     * 
     * @return the issue number text property
     */
    public StringProperty issueNumberProperty() {
        return this.issueNumberText;
    }

    /**
     * Adds a new comic to the currently selected collection using the stored title
     * and issue number text.
     * 
     * @throws IllegalArgumentException if the issue number is not a valid positive
     *                                  integer or if the title is invalid
     * @throws IllegalStateException    if no collection is selected in the
     *                                  MainWindowViewModel
     */
    public void addComic() {
        String currentTitle = this.title.get();
        String issueText = this.issueNumberText.get();

        int issueNumber;
        try {
            issueNumber = Integer.parseInt(issueText);
        } catch (NumberFormatException exception) {
            throw new IllegalArgumentException("Issue number must be a positive integer", exception);
        }

        if (issueNumber <= 0) {
            throw new IllegalArgumentException("Issue number must be a positive integer");
        }

        this.mainWindowViewModel.addComicToSelectedCollection(currentTitle, issueNumber);

        this.title.set("");
        this.issueNumberText.set("");
    }
}
