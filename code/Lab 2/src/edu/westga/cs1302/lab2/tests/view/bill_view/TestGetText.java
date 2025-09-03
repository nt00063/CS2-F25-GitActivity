package edu.westga.cs1302.lab2.tests.view.bill_view;

import edu.westga.cs1302.lab2.model.Bill;
import edu.westga.cs1302.lab2.model.BillItem;
import edu.westga.cs1302.lab2.view.BillView;

import static org. junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

public class TestGetText {
	
	@Test public void testGetTextWithEmptyBill() {
		Bill bill = new Bill();
		BillView view = new BillView();
		
		String text = view.getText(bill);
		
		assertTrue(text.contains("ITEMS"));
		assertTrue(text.contains("SUBTOTAL - $0.0"));
		assertTrue(text.contains("TAX - $0.0"));
		assertTrue(text.contains("TIP - $0.0"));
		assertTrue(text.contains("TOTAL - $0.0"));
	}
	

}
