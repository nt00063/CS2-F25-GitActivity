package edu.westga.cs1302.contact_manager.tests.viewmodel.main_window_view_model;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import edu.westga.cs1302.project3.viewmodel.MainWindowViewModel;

/**
 * Tests for the constructor of MainWindowViewModel.
 * 
 * @author CS 1302
 * @version Fall 2025
 */
class TestConstructor {

	@Test
	void testInitialState() {
		MainWindowViewModel vm = new MainWindowViewModel();

		assertNotNull(vm.getName());
		assertNotNull(vm.getPhoneNumber());
		assertNotNull(vm.getSearchCriteria());
		assertNotNull(vm.getContacts());

		assertEquals("", vm.getName().get());
		assertEquals("", vm.getPhoneNumber().get());
		assertEquals("", vm.getSearchCriteria().get());
		assertEquals(0, vm.getContacts().size());
	}
}
