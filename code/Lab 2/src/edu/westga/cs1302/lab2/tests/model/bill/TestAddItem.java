package edu.westga.cs1302.lab2.tests.model.bill;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import edu.westga.cs1302.lab2.model.BillItem;
import edu.westga.cs1302.lab2.model.Bill;

public class TestAddItem {
	
	@Test
	public void testNullItem() {
		Bill bill = new Bill();
		assertThrows(IllegalArgumentException.class, () -> {
			bill.addItem(null);
		});
	}
	
	@Test
	public void testAddOneItem() {
		Bill bill = new Bill();
		BillItem item = new BillItem("Burger", 10.0);
		
		bill.addItem(item);;
		
		assertEquals(1, bill.getItems().size());
		assertSame(item, bill.getItems().get(0));
	}

}
