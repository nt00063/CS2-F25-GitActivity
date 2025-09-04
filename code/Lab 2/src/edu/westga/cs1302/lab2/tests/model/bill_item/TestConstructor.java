package edu.westga.cs1302.lab2.tests.model.bill_item;

import edu.westga.cs1302.lab2.model.BillItem;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

/**
 * Tests the BillItem class and its constructor
 * 
 * @author CS 1302
 * @version Fall 2025
 */
public class TestConstructor {
	
	/**
	 * Tests if the constructor is valid
	 */
	@Test
	public void testValidConstructor() {
		BillItem item = new BillItem("Pizza", 12.5);
		
		assertEquals("Pizza", item.getName());
		assertEquals(12.5, item.getAmount(), 0.0001);
	}
	
	/**
	 * Tests when the Constructor has a null name
	 */
	@Test
	public void testConstructorNullName() {
		assertThrows(IllegalArgumentException.class, () -> {
			new BillItem(null, 10.0);
		});
	}

	/**
	 * Tests when the constructor has a zero amount
	 */
	@Test
	public void testConstructorZeroAmount() {
		assertThrows(IllegalArgumentException.class, () -> {
			new BillItem("Salad", 0.0);
		});
	}
	
	/**
	 * Tests when the constructor has a negative amount
	 */
	@Test
	public void testConstructorNegativeAmount() {
		assertThrows(IllegalArgumentException.class, () -> {
			new BillItem("Soup", -5.0);
		});
	}
}
