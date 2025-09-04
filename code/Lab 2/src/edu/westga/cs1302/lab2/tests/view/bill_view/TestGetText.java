package edu.westga.cs1302.lab2.tests.view.bill_view;

import edu.westga.cs1302.lab2.model.Bill;
import edu.westga.cs1302.lab2.model.BillItem;
import edu.westga.cs1302.lab2.view.BillView;

import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;

/**
 * Tests the BillVew class and its getText method
 * 
 * @author CS 1302
 * @version Fall 2025
 */
public class TestGetText {
	
	/**
	 * Tests when the Bill is empty
	 */
	@Test 
	public void testGetTextWithEmptyBill() {
		Bill bill = new Bill();
		BillView view = new BillView();
		
		String text = view.getText(bill);
		
		assertTrue(text.contains("ITEMS"));
		assertTrue(text.contains("SUBTOTAL - $0.0"));
		assertTrue(text.contains("TAX - $0.0"));
		assertTrue(text.contains("TIP - $0.0"));
		assertTrue(text.contains("TOTAL - $0.0"));
	}
	
	/**
	 * Tests when the bill has one item
	 */
	@Test
	public void testGetTextOneItem() {
		Bill bill = new Bill();
		bill.addItem(new BillItem("Burger", 10.0));
		BillView view = new BillView();
		
		String text = view.getText(bill);
		
		 assertTrue(text.contains("Burger - 10.0"));
	     assertTrue(text.contains("SUBTOTAL - $10.0"));
	     assertTrue(text.contains("TAX - $1.0"));
	     assertTrue(text.contains("TIP - $2.0"));
	     assertTrue(text.contains("TOTAL - $13.0"));
	}
	
	/**
	 * Tests when the bill has multiple items
	 */
	@Test
	public void testGetTextMultipleItems() {
		Bill bill = new Bill();
		bill.addItem(new BillItem("Fries", 3.0));
		bill.addItem(new BillItem("Soda", 2.0));
		BillView view = new BillView();
		
		String text = view.getText(bill);
		
		assertTrue(text.contains("Fries - 3.0"));
		assertTrue(text.contains("Soda - 2.0"));
        assertTrue(text.contains("SUBTOTAL - $5.0"));
        assertTrue(text.contains("TAX - $0.5"));
        assertTrue(text.contains("TIP - $1.0"));
        assertTrue(text.contains("TOTAL - $6.5"));
	}

}
