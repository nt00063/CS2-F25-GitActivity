package edu.westga.cs1302.lab2.tests.model.bill_item;

import edu.westga.cs1302.lab2.model.BillItem;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

public class TestConstructor {
	
	@Test
	public void testValidConstructor() {
		BillItem item = new BillItem("Pizza", 12.5);
		
		assertEquals("Pizza", item.getName());
		assertEquals(12.5, item.getAmount(), 0.0001);
	}
	
	@Test
	public void testConstructorNullName() {
		assertThrows(IllegalArgumentException.class, () -> {
			new BillItem(null, 10.0);
		});
	}

	@Test
	public void testConstructorZeroAmount() {
		assertThrows(IllegalArgumentException.class, () -> {
			new BillItem("Salad", 0.0);
		});
	}
	
	
}
