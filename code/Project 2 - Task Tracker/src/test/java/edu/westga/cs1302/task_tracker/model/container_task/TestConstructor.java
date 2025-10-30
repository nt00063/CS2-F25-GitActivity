package edu.westga.cs1302.task_tracker.model.container_task;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import edu.westga.cs1302.task_tracker.model.ContainerTask;
import edu.westga.cs1302.task_tracker.model.Task.TaskPriority;

/** Tests the constructor of the ContainerTask class.
 * 
 * @author CS 1302
 * @version Fall 2025
 */
class TestConstructor {

	@Test
	void testConstructorCreatesEmptySubtaskList() {
		ContainerTask container = new ContainerTask("Parent", "Has subtasks", TaskPriority.HIGH);
		assertTrue(container.getSubTasks().isEmpty());
	}
}
