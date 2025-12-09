package edu.westga.cs1302.contact_manager.tests.viewmodel.main_window_view_model;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import edu.westga.cs1302.project3.model.Contact;
import edu.westga.cs1302.project3.viewmodel.MainWindowViewModel;

/**
 * Tests for the addContact method in MainWindowViewModel.
 * 
 * @author CS 1302
 * @version Fall 2025
 */
class TestAddContact {

	@Test
	void testAddValidContact() {
		MainWindowViewModel vm = new MainWindowViewModel();

		vm.getName().set("Alice");
		vm.getPhoneNumber().set("123-4567");

		vm.addContact();

		assertEquals(1, vm.getContacts().size());
		Contact stored = vm.getContacts().get(0);
		assertEquals("Alice", stored.getName());
		assertEquals("123-4567", stored.getPhoneNumber());
	}

	@Test
	void testAddContactWithInvalidNameThrowsException() {
		MainWindowViewModel vm = new MainWindowViewModel();

		vm.getName().set("Alice1"); // invalid name (contains digit)
		vm.getPhoneNumber().set("123-4567");

		assertThrows(IllegalArgumentException.class, () -> vm.addContact());
	}

	@Test
	void testAddContactWithInvalidPhoneThrowsException() {
		MainWindowViewModel vm = new MainWindowViewModel();

		vm.getName().set("Alice");
		vm.getPhoneNumber().set("123456"); // invalid phone (6 digits)

		assertThrows(IllegalArgumentException.class, () -> vm.addContact());
	}

	@Test
	void testAddContactWithDuplicateNameThrowsException() {
		MainWindowViewModel vm = new MainWindowViewModel();

		vm.getName().set("Alice");
		vm.getPhoneNumber().set("123-4567");
		vm.addContact();

		vm.getName().set("Alice");      // duplicate name
		vm.getPhoneNumber().set("765-4321");

		IllegalArgumentException ex = assertThrows(IllegalArgumentException.class, () -> vm.addContact());
		assertTrue(ex.getMessage().toLowerCase().contains("name"));
	}

	@Test
	void testAddContactWithDuplicatePhoneSameFormatThrowsException() {
		MainWindowViewModel vm = new MainWindowViewModel();

		vm.getName().set("Alice");
		vm.getPhoneNumber().set("123-4567");
		vm.addContact();

		vm.getName().set("Bob");
		vm.getPhoneNumber().set("123-4567"); // same exact phone number

		IllegalArgumentException ex = assertThrows(IllegalArgumentException.class, () -> vm.addContact());
		assertTrue(ex.getMessage().toLowerCase().contains("phone"));
	}

	@Test
	void testAddContactWithDuplicatePhoneDifferentFormatThrowsException() {
		MainWindowViewModel vm = new MainWindowViewModel();

		vm.getName().set("Alice");
		vm.getPhoneNumber().set("123-4567");
		vm.addContact();

		vm.getName().set("Bob");
		vm.getPhoneNumber().set("1234567"); // same logical number, no dash

		IllegalArgumentException ex = assertThrows(IllegalArgumentException.class, () -> vm.addContact());
		assertTrue(ex.getMessage().toLowerCase().contains("phone"));
	}
}
