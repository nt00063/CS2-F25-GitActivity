package edu.westga.cs1302.password_generator.viewmodel.viewmodel;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import edu.westga.cs1302.password_generator.viewmodel.ViewModel;

class TestIsInputValid {

    @Test
    void testInvalidWhenLengthNotANumber() {
        ViewModel vm = new ViewModel();
        vm.getMinimumLength().setValue("abc");
        vm.getRequireDigits().setValue(true);
        assertFalse(vm.isInputValid());
    }

    @Test
    void testInvalidWhenLengthLessThanOne() {
        ViewModel vm = new ViewModel();
        vm.getMinimumLength().setValue("0");
        vm.getRequireDigits().setValue(true);
        assertFalse(vm.isInputValid());
    }

    @Test
    void testInvalidWhenNoRequirementsSelected() {
        ViewModel vm = new ViewModel();
        vm.getMinimumLength().setValue("5");
        vm.getRequireDigits().setValue(false);
        vm.getRequireLowercase().setValue(false);
        vm.getRequireUppercase().setValue(false);
        assertFalse(vm.isInputValid());
    }

    @Test
    void testValidWhenDigitsSelected() {
        ViewModel vm = new ViewModel();
        vm.getMinimumLength().setValue("3");
        vm.getRequireDigits().setValue(true);
        assertTrue(vm.isInputValid());
    }

    @Test
    void testValidWhenLowercaseSelected() {
        ViewModel vm = new ViewModel();
        vm.getMinimumLength().setValue("4");
        vm.getRequireLowercase().setValue(true);
        assertTrue(vm.isInputValid());
    }

    @Test
    void testValidWhenUppercaseSelected() {
        ViewModel vm = new ViewModel();
        vm.getMinimumLength().setValue("6");
        vm.getRequireUppercase().setValue(true);
        assertTrue(vm.isInputValid());
    }

    @Test
    void testValidWhenMultipleRequirementsSelected() {
        ViewModel vm = new ViewModel();
        vm.getMinimumLength().setValue("8");
        vm.getRequireDigits().setValue(true);
        vm.getRequireLowercase().setValue(true);
        vm.getRequireUppercase().setValue(true);
        assertTrue(vm.isInputValid());
    }
}
