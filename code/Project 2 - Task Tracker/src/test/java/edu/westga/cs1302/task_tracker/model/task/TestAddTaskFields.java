package edu.westga.cs1302.task_tracker.model.task;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import edu.westga.cs1302.task_tracker.model.ContainerTask;
import edu.westga.cs1302.task_tracker.model.Task;
import edu.westga.cs1302.task_tracker.model.Task.TaskPriority;

/** Verifies that Task.addTask returns a ContainerTask with same core fields. */
class TestAddTaskFields {

	@Test
	void testReturnedContainerTaskCopiesParentFields() {
		Task parent = new Task("Parent", "Desc", TaskPriority.HIGH);
		Task child = new Task("Child", "C", TaskPriority.LOW);

		Task result = parent.addTask(child);
		assertTrue(result instanceof ContainerTask);
		assertEquals("Parent", result.getName());
		assertEquals("Desc", result.getDescription());
		assertEquals(TaskPriority.HIGH, result.getPriority());
	}
}
