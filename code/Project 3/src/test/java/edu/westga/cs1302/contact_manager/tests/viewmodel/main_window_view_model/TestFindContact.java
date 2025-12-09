package edu.westga.cs1302.contact_manager.tests.viewmodel.main_window_view_model;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import edu.westga.cs1302.project3.viewmodel.MainWindowViewModel;

/**
 * Tests for the findContact method in MainWindowViewModel.
 * 
 * @author CS 1302
 * @version Fall 2025
 */
class TestFindContact {

	@Test
	void testFindByNameReturnsContactString() {
		MainWindowViewModel vm = new MainWindowViewModel();

		vm.getName().set("Alice");
		vm.getPhoneNumber().set("123-4567");
		vm.addContact();

		vm.getSearchCriteria().set("Alice");
		String result = vm.findContact();

		assertEquals("Alice, 123-4567", result);
	}

	@Test
	void testFindByPhoneWithDashReturnsContactString() {
		MainWindowViewModel vm = new MainWindowViewModel();

		vm.getName().set("Alice");
		vm.getPhoneNumber().set("123-4567");
		vm.addContact();

		vm.getSearchCriteria().set("123-4567");
		String result = vm.findContact();

		assertEquals("Alice, 123-4567", result);
	}

	@Test
	void testFindByPhoneWithoutDashReturnsContactString() {
		MainWindowViewModel vm = new MainWindowViewModel();

		vm.getName().set("Alice");
		vm.getPhoneNumber().set("123-4567");
		vm.addContact();

		// Search with logically equivalent number, no dash
		vm.getSearchCriteria().set("1234567");
		String result = vm.findContact();

		assertEquals("Alice, 123-4567", result);
	}

	@Test
	void testFindContactWithInvalidCriteriaThrowsException() {
		MainWindowViewModel vm = new MainWindowViewModel();

		vm.getSearchCriteria().set("invalid123"); // neither valid name nor phone

		assertThrows(IllegalArgumentException.class, () -> vm.findContact());
	}

	@Test
	void testFindContactWhenNoMatchReturnsNoContactFound() {
		MainWindowViewModel vm = new MainWindowViewModel();

		vm.getName().set("Alice");
		vm.getPhoneNumber().set("123-4567");
		vm.addContact();

		// Valid name format that does not exist
		vm.getSearchCriteria().set("Bob");
		String result = vm.findContact();

		assertEquals("No contact found.", result);
	}
}
