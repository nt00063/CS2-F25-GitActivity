package edu.westga.cs1302.password_generator.tests.viewmodel.password_generator;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import edu.westga.cs1302.password_generator.viewmodel.PasswordViewModel;

/**
 * Tests for the PasswordViewModel class.
 * 
 * @author CS 1302
 * @version Fall 2025
 */
class TestPasswordViewModel {

    private PasswordViewModel vm;

    @BeforeEach
    void setUp() {
        this.vm = new PasswordViewModel();
    }

    @Test
    void testDefaultPropertyValues() {
        assertEquals(1, this.vm.minimumLengthProperty().get());
        assertFalse(this.vm.mustIncludeDigitsProperty().get());
        assertFalse(this.vm.mustIncludeLowerCaseProperty().get());
        assertFalse(this.vm.mustIncludeUpperCaseProperty().get());
        assertEquals("", this.vm.generatedPasswordProperty().get());
        assertEquals("", this.vm.errorMessageProperty().get());
    }

    @Test
    void testGeneratePasswordProducesResult() {
        this.vm.minimumLengthProperty().set(3);
        this.vm.mustIncludeDigitsProperty().set(true);
        this.vm.mustIncludeLowerCaseProperty().set(true);
        this.vm.mustIncludeUpperCaseProperty().set(true);

        this.vm.generatePassword();

        assertFalse(this.vm.generatedPasswordProperty().get().isEmpty(),
                "Expected generated password not to be empty");
        assertEquals("", this.vm.errorMessageProperty().get(),
                "No error should be reported when valid data is used");
    }

    @Test
    void testGeneratePasswordInvalidLengthSetsError() {
        this.vm.minimumLengthProperty().set(0);
        this.vm.generatePassword();

        assertTrue(this.vm.errorMessageProperty().get().contains("Invalid input"));
        assertEquals("", this.vm.generatedPasswordProperty().get());
    }
}
