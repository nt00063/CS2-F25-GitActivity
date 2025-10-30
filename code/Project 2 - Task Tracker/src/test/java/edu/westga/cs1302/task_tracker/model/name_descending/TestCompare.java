package edu.westga.cs1302.task_tracker.model.name_descending;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import edu.westga.cs1302.task_tracker.model.NameDescending;
import edu.westga.cs1302.task_tracker.model.Task;
import edu.westga.cs1302.task_tracker.model.Task.TaskPriority;

/** Tests NameDescending comparator behavior (Z→A, case-insensitive). */
class TestCompare {

	@Test
	void testNullFirstThrows() {
		Task b = new Task("Beta", "", TaskPriority.LOW);
		assertThrows(IllegalArgumentException.class, () -> new NameDescending().compare(null, b));
	}

	@Test
	void testNullSecondThrows() {
		Task a = new Task("Alpha", "", TaskPriority.HIGH);
		assertThrows(IllegalArgumentException.class, () -> new NameDescending().compare(a, null));
	}

	@Test
	void testReverseAlphabeticalOrder() {
		Task a = new Task("Alpha", "", TaskPriority.HIGH);
		Task b = new Task("Beta", "", TaskPriority.LOW);
		assertTrue(new NameDescending().compare(a, b) > 0);
		assertTrue(new NameDescending().compare(b, a) < 0);
	}

	@Test
	void testCaseInsensitivity() {
		Task a = new Task("zebra", "", TaskPriority.MEDIUM);
		Task b = new Task("ZEBRA", "", TaskPriority.MEDIUM);
		assertEquals(0, new NameDescending().compare(a, b));
	}
}
