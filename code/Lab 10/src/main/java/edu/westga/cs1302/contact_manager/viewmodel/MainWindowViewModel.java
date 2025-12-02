package edu.westga.cs1302.contact_manager.viewmodel;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import edu.westga.cs1302.contact_manager.model.Contact;
import javafx.beans.property.ListProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;

/** View model for the MainWindow view
 * 
 * @author CS 1302
 * @version Fall 2025
 */
public class MainWindowViewModel {
	private StringProperty name;
	private StringProperty phoneNumber;
	private StringProperty searchCriteria;
	private ListProperty<Contact> contacts;

	private Map<String, Contact> contactsByName;
	private Map<String, Contact> contactsByPhoneNumber;
	
	/** Initialize the MainWindowViewModel
	 * 
	 * @precondition none
	 * @postcondition none
	 * 
	 */
	public MainWindowViewModel() {
		this.name = new SimpleStringProperty("");
		this.phoneNumber = new SimpleStringProperty("");
		this.searchCriteria = new SimpleStringProperty("");
		this.contacts = new SimpleListProperty<Contact>(
				FXCollections.observableList(new ArrayList<Contact>()));

		this.contactsByName = new HashMap<String, Contact>();
		this.contactsByPhoneNumber = new HashMap<String, Contact>();
	}
	
	/** Return the name property used when adding a contact
	 * 
	 * @precondition none
	 * @postcondition none
	 * 
	 * @return the name property used when adding a contact
	 */
	public StringProperty getName() {
		return this.name;
	}
	
	/** Return the phone number property used when adding a contact
	 * 
	 * @precondition none
	 * @postcondition none
	 * 
	 * @return the phone number property used when adding a contact
	 */
	public StringProperty getPhoneNumber() {
		return this.phoneNumber;
	}
	
	/** Return the search criteria property used when finding a contact
	 * 
	 * @precondition none
	 * @postcondition none
	 * 
	 * @return the search criteria property used when finding a contact
	 */
	public StringProperty getSearchCriteria() {
		return this.searchCriteria;
	}
	
	/** Return the list property containing all contacts added to the system
	 * 
	 * @precondition none
	 * @postcondition none
	 * 
	 * @return the list property containing all contacts added to the system
	 */
	public ListProperty<Contact> getContacts() {
		return this.contacts;
	}

	/** Normalize a phone number string so logically equivalent numbers
	 *  (such as 123-4567 and 1234567) are treated as the same value.
	 * 
	 * @precondition none
	 * @postcondition none
	 * 
	 * @param phoneNumber the phone number to normalize
	 * @return the normalized phone number, or null if phoneNumber is null
	 */
	private String normalizePhoneNumber(String phoneNumber) {
		if (phoneNumber == null) {
			return null;
		}
		return phoneNumber.replace("-", "");
	}
	
	/** Adds a new contact with name and phone number set by the appropriate property
	 * 
	 * @precondition none
	 * @postcondition a new contact with name and phone number provided has been added
	 * 
	 * @throws IllegalArgumentException if either name or phone number are invalid (see Contact class)
	 *                                  or if a contact already exists with the same name or phone number
	 */
	public void addContact() throws IllegalArgumentException {
		String nameValue = this.name.get();
		String phoneValue = this.phoneNumber.get();
		String phoneKey = this.normalizePhoneNumber(phoneValue);

		// Duplicate checks before constructing the Contact
		if (this.contactsByName.containsKey(nameValue)) {
			throw new IllegalArgumentException("A contact with this name already exists.");
		}
		if (this.contactsByPhoneNumber.containsKey(phoneKey)) {
			throw new IllegalArgumentException("A contact with this phone number already exists.");
		}

		// Let Contact enforce format validation
		Contact newContact = new Contact(nameValue, phoneValue);

		this.contacts.add(newContact);
		this.contactsByName.put(nameValue, newContact);
		this.contactsByPhoneNumber.put(phoneKey, newContact);
	}
	
	/** Finds a contact with name or phone number matches provide search criteria
	 * 
	 * @precondition none
	 * @postcondition getResultContact().get() is set to the appropriate contact (if contact found) OR null (if no contact found)
	 * 
	 * @return A string representation of the contact found.
	 */
	public String findContact() {
		String criteria = this.searchCriteria.get();

		// Search by name
		if (Contact.checkName(criteria)) {
			Contact found = this.contactsByName.get(criteria);
			if (found == null) {
				return "No contact found.";
			}
			return found.toString();
		}

		// Search by phone number (normalized)
		if (Contact.checkPhoneNumber(criteria)) {
			String phoneKey = this.normalizePhoneNumber(criteria);
			Contact found = this.contactsByPhoneNumber.get(phoneKey);
			if (found == null) {
				return "No contact found.";
			}
			return found.toString();
		}

		throw new IllegalArgumentException("Search criteria is not a valid name or phone number");
	}
}
