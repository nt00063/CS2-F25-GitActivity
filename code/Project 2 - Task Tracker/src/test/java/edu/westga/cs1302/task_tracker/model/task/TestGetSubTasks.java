package edu.westga.cs1302.task_tracker.model.task;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.Test;

import edu.westga.cs1302.task_tracker.model.Task;
import edu.westga.cs1302.task_tracker.model.Task.TaskPriority;

/** Tests default getSubTasks behavior for a regular Task. */
class TestGetSubTasks {

	@Test
	void testReturnsEmptyUnmodifiableList() {
		Task t = new Task("T", "", TaskPriority.LOW);
		List<Task> subs = t.getSubTasks();
		assertTrue(subs.isEmpty());
		assertThrows(UnsupportedOperationException.class, () -> subs.add(t));
	}
}
