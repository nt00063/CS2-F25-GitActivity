package edu.westga.cs1302.task_tracker.model.name_descending;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import edu.westga.cs1302.task_tracker.model.NameDescending;

/** Tests the toString for NameDescending. */
class TestToString {

	@Test
	void testToStringLabel() {
		assertEquals("Name (Z→A)", new NameDescending().toString());
	}
}
