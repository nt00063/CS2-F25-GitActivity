package edu.westga.cs1302.password_generator.viewmodel;

import edu.westga.cs1302.password_generator.model.PasswordGenerator;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 * ViewModel for the Password Generator application.
 * Acts as a bridge between the View (UI) and the Model (PasswordGenerator).
 * 
 * @author CS 1302
 * @version Fall 2025
 */
public class PasswordViewModel {

    private final IntegerProperty minimumLength;
    private final BooleanProperty mustIncludeDigits;
    private final BooleanProperty mustIncludeLowerCase;
    private final BooleanProperty mustIncludeUpperCase;
    private final StringProperty generatedPassword;
    private final StringProperty errorMessage;

    private final PasswordGenerator generator;

    /**
     * Creates a new PasswordViewModel with default values.
     * 
     * @precondition none
     * @postcondition properties initialized
     */
    public PasswordViewModel() {
        this.generator = new PasswordGenerator(System.currentTimeMillis());
        this.minimumLength = new SimpleIntegerProperty(1);
        this.mustIncludeDigits = new SimpleBooleanProperty(false);
        this.mustIncludeLowerCase = new SimpleBooleanProperty(false);
        this.mustIncludeUpperCase = new SimpleBooleanProperty(false);
        this.generatedPassword = new SimpleStringProperty("");
        this.errorMessage = new SimpleStringProperty("");
    }

    /**
     * Minimum length Property
     * 
     * @return this.minimumLength the minimum length
     */
    public IntegerProperty minimumLengthProperty() {
        return this.minimumLength;
    }

    /**
     * Include digits property
     * 
     * @return this.mustIncludeDigits the Include digits property
     */
    public BooleanProperty mustIncludeDigitsProperty() {
        return this.mustIncludeDigits;
    }

    /**
     * Include Lower Case Property
     * 
     * @return this.mustIncludeLowerCase the Include lower case property
     */
    public BooleanProperty mustIncludeLowerCaseProperty() {
        return this.mustIncludeLowerCase;
    }

    /**
     * Include Upper Case Property
     * 
     * @return this.mustIncludeUpperCase the Include Upper Case property
     */
    public BooleanProperty mustIncludeUpperCaseProperty() {
        return this.mustIncludeUpperCase;
    }

    /**
     * Generated Password Property
     * 
     * @return this.generatedPassword the generate password property
     */
    public StringProperty generatedPasswordProperty() {
        return this.generatedPassword;
    }

    /**
     * Error Message Property
     * 
     * @return this.errorMessage the error message property
     */
    public StringProperty errorMessageProperty() {
        return this.errorMessage;
    }

    /**
     * Generates a password based on the current property values.
     */
    public void generatePassword() {
        try {
            this.generator.setMinimumLength(this.minimumLength.get());
            this.generator.setMustHaveAtLeastOneDigit(this.mustIncludeDigits.get());
            this.generator.setMustHaveAtLeastOneLowerCaseLetter(this.mustIncludeLowerCase.get());
            this.generator.setMustHaveAtLeastOneUpperCaseLetter(this.mustIncludeUpperCase.get());

            String password = this.generator.generatePassword();
            this.generatedPassword.set(password);
            this.errorMessage.set("");

        } catch (IllegalArgumentException error) {
            this.errorMessage.set("Invalid input: " + error.getMessage());
            this.generatedPassword.set("");
        } catch (Exception error) {
            this.errorMessage.set("Unexpected error: " + error.getMessage());
            this.generatedPassword.set("");
        }
    }
}
