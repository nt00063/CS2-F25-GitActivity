package edu.westga.cs1302.task_tracker.model.name_ascending;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import edu.westga.cs1302.task_tracker.model.NameAscending;

/** Tests the toString for NameAscending. */
class TestToString {

	@Test
	void testToStringLabel() {
		assertEquals("Name (A→Z)", new NameAscending().toString());
	}
}
