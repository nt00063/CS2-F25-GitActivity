package edu.westga.cs1302.task_tracker.model.descending;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import edu.westga.cs1302.task_tracker.model.Descending;

/** Tests the toString for the Descending (priority) comparator. */
class TestToString {

	@Test
	void testToStringShowsPriorityDescending() {
		assertEquals("Priority (Descending)", new Descending().toString());
	}
}

