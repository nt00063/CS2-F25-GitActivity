package edu.westga.cs1302.task_tracker.model.container_task;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import edu.westga.cs1302.task_tracker.model.ContainerTask;
import edu.westga.cs1302.task_tracker.model.Task.TaskPriority;

/** Tests the toString method of the ContainerTask class.
 * 
 * @author CS 1302
 * @version Fall 2025
 */
class TestToString {

	@Test
	void testToStringShowsPlusIndicator() {
		ContainerTask container = new ContainerTask("Clean Room", "With subtasks", TaskPriority.HIGH);
		assertTrue(container.toString().contains("(+)"));
	}
}
