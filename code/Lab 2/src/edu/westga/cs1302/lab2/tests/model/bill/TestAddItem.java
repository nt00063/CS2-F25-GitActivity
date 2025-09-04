package edu.westga.cs1302.lab2.tests.model.bill;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

import edu.westga.cs1302.lab2.model.BillItem;
import edu.westga.cs1302.lab2.model.Bill;

/**
 * Tests the Bill class and the AddItem method
 * 
 * @author CS 1302
 * @version Fall 2025
 */
public class TestAddItem {
	
	/**
	 * Tests when the list is null
	 */
	@Test
	public void testNullItem() {
		Bill bill = new Bill();
		assertThrows(IllegalArgumentException.class, () -> {
			bill.addItem(null);
		});
	}
	
	/**
	 * Tests when the list has one item
	 */
	@Test
	public void testAddOneItem() {
		Bill bill = new Bill();
		BillItem item = new BillItem("Burger", 10.0);
		
		bill.addItem(item);
		
		assertEquals(1, bill.getItems().size());
		assertSame(item, bill.getItems().get(0));
	}

}
