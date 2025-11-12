package edu.westga.cs1302.password_generator.viewmodel;

import java.util.Random;

import edu.westga.cs1302.password_generator.model.PasswordGenerator;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 * Manages utilizing the model and makes properties available to bind the UI elements.
 * 
 * This version has been updated to:
 * - Validate minimum length
 * - Store a running list of generated passwords
 * - Provide an ObservableList for ListView binding
 * 
 * @author CS 1302
 * @version Fall 2025
 */
public class ViewModel {

    private StringProperty minimumLength;
    private BooleanProperty requireDigits;
    private BooleanProperty requireLowercase;
    private BooleanProperty requireUppercase;

    private StringProperty password;
    private StringProperty errorText;

    /** 
     * A list containing ALL previously generated passwords.
     * Used for binding to the ListView in the UI. 
     */
    private ObservableList<String> passwordList;

    private PasswordGenerator generator;

    /**
     * Initialize the properties for the view model.
     */
    public ViewModel() {
        this.minimumLength = new SimpleStringProperty("1");
        this.requireDigits = new SimpleBooleanProperty(false);
        this.requireLowercase = new SimpleBooleanProperty(false);
        this.requireUppercase = new SimpleBooleanProperty(false);

        this.password = new SimpleStringProperty("");
        this.errorText = new SimpleStringProperty("");

        this.passwordList = FXCollections.observableArrayList();

        Random randomNumberGenerator = new Random();
        this.generator = new PasswordGenerator(randomNumberGenerator.nextLong());
    }

    /**
     * Return the minimum length property.
     * 
     * @return the minimum length property
     */
    public StringProperty getMinimumLength() {
        return this.minimumLength;
    }

    /**
     * Return the require digits property.
     * 
     * @return the require digits property
     */
    public BooleanProperty getRequireDigits() {
        return this.requireDigits;
    }

    /**
     * Return the require upper case property.
     * 
     * @return the require upper case property
     */
    public BooleanProperty getRequireUppercase() {
        return this.requireUppercase;
    }

    /**
     * Return the require lower case property.
     * 
     * @return the require lower case property
     */
    public BooleanProperty getRequireLowercase() {
        return this.requireLowercase;
    }

    /**
     * Return the password property.
     * 
     * @return the password property
     */
    public StringProperty getPassword() {
        return this.password;
    }

    /**
     * Return the error text property.
     * 
     * @return the error text property
     */
    public StringProperty getErrorText() {
        return this.errorText;
    }

    /**
     * Returns the observable list of all generated passwords.
     * 
     * @return ObservableList of passwords
     */
    public ObservableList<String> passwordListProperty() {
        return this.passwordList;
    }

    /**
     * Generates a password using the minimum length, require digit,
     * require lower case, and require upper case property values.
     * 
     * If generation succeeds:
     *  - errorText is cleared
     *  - password property is updated
     *  - newly generated password is added to passwordList
     * 
     * If an error is encountered:
     *  - password property is cleared
     *  - errorText property is populated
     */
    public void generatePassword() {
        int minimum = -1;
        this.password.set("");

        try {
            minimum = Integer.parseInt(this.minimumLength.get());
        } catch (NumberFormatException nfe) {
            this.errorText.set("Minimum length must be a positive integer.");
            return;
        }

        try {
            this.generator.setMinimumLength(minimum);
        } catch (IllegalArgumentException iae) {
            this.errorText.set("Invalid Minimum Length: " + iae.getMessage());
            return;
        }

        this.generator.setMustHaveAtLeastOneDigit(this.requireDigits.get());
        this.generator.setMustHaveAtLeastOneLowerCaseLetter(this.requireLowercase.get());
        this.generator.setMustHaveAtLeastOneUpperCaseLetter(this.requireUppercase.get());

        String result = this.generator.generatePassword();

        this.password.set(result);
        this.errorText.set("");
        this.passwordList.add(result);
    }
}
