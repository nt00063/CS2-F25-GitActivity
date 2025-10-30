package edu.westga.cs1302.task_tracker.model.ascending;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import edu.westga.cs1302.task_tracker.model.Ascending;

/** Tests the toString for the Ascending (priority) comparator. */
class TestToString {

	@Test
	void testToStringShowsPriorityAscending() {
		assertEquals("Priority (Ascending)", new Ascending().toString());
	}
}
