package edu.westga.cs1302.task_tracker.model.name_ascending;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import edu.westga.cs1302.task_tracker.model.NameAscending;
import edu.westga.cs1302.task_tracker.model.Task;
import edu.westga.cs1302.task_tracker.model.Task.TaskPriority;

/** Tests NameAscending comparator behavior (A→Z, case-insensitive). */
class TestCompare {

	@Test
	void testNullFirstThrows() {
		Task b = new Task("Beta", "", TaskPriority.LOW);
		assertThrows(IllegalArgumentException.class, () -> new NameAscending().compare(null, b));
	}

	@Test
	void testNullSecondThrows() {
		Task a = new Task("Alpha", "", TaskPriority.HIGH);
		assertThrows(IllegalArgumentException.class, () -> new NameAscending().compare(a, null));
	}

	@Test
	void testAlphabeticalOrder() {
		Task a = new Task("Alpha", "", TaskPriority.HIGH);
		Task b = new Task("Beta", "", TaskPriority.LOW);
		assertTrue(new NameAscending().compare(a, b) < 0);
		assertTrue(new NameAscending().compare(b, a) > 0);
	}

	@Test
	void testCaseInsensitivity() {
		Task a = new Task("apple", "", TaskPriority.MEDIUM);
		Task b = new Task("Apple", "", TaskPriority.MEDIUM);
		assertEquals(0, new NameAscending().compare(a, b));
	}
}
