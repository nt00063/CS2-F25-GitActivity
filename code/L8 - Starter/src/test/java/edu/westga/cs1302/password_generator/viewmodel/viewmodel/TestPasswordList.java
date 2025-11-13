package edu.westga.cs1302.password_generator.viewmodel.viewmodel;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import edu.westga.cs1302.password_generator.viewmodel.ViewModel;

public class TestPasswordList {

    @Test
    void testListStartsEmpty() {
        ViewModel vm = new ViewModel();

        assertEquals(0, vm.passwordListProperty().size());
    }

    @Test
    void testSinglePasswordAdded() {
        ViewModel vm = new ViewModel();
        vm.getMinimumLength().set("6");

        vm.generatePassword();

        assertEquals(1, vm.passwordListProperty().size());
    }

    @Test
    void testMultiplePasswordsAdded() {
        ViewModel vm = new ViewModel();
        vm.getMinimumLength().set("5");

        vm.generatePassword();
        vm.generatePassword();
        vm.generatePassword();

        assertEquals(3, vm.passwordListProperty().size());
    }
}
