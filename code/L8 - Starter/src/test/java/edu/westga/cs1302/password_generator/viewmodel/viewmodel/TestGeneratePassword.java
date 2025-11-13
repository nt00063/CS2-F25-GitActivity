package edu.westga.cs1302.password_generator.viewmodel.viewmodel;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import edu.westga.cs1302.password_generator.viewmodel.ViewModel;

public class TestGeneratePassword {

    @Test
    void testGenerateValidPassword() {
        ViewModel vm = new ViewModel();
        vm.getMinimumLength().set("5");

        vm.generatePassword();

        assertNotEquals("", vm.getPassword().get());
        assertEquals("", vm.getErrorText().get());
    }

    @Test
    void testPasswordAddedToList() {
        ViewModel vm = new ViewModel();
        vm.getMinimumLength().set("4");

        vm.generatePassword();
        vm.generatePassword();

        assertEquals(2, vm.passwordListProperty().size());
    }

    @Test
    void testInvalidLengthProducesError() {
        ViewModel vm = new ViewModel();
        vm.getMinimumLength().set("0");

        vm.generatePassword();

        assertTrue(vm.getErrorText().get().contains("Invalid"));
        assertEquals("", vm.getPassword().get());
    }
}
