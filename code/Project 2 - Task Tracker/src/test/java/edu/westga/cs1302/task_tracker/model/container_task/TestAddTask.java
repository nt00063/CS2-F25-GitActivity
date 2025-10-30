package edu.westga.cs1302.task_tracker.model.container_task;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.Test;

import edu.westga.cs1302.task_tracker.model.ContainerTask;
import edu.westga.cs1302.task_tracker.model.Task;
import edu.westga.cs1302.task_tracker.model.Task.TaskPriority;

/** Tests the addTask and getSubTasks methods of the ContainerTask class.
 * 
 * @author CS 1302
 * @version Fall 2025
 */
class TestAddTask {

	@Test
	void testAddTaskStoresSubtask() {
		ContainerTask container = new ContainerTask("Parent", "Has subtasks", TaskPriority.MEDIUM);
		Task sub = new Task("Subtask", "Child", TaskPriority.LOW);

		container.addTask(sub);

		List<Task> subtasks = container.getSubTasks();
		assertEquals(1, subtasks.size());
		assertEquals("Subtask", subtasks.get(0).getName());
	}

	@Test
	void testAddTaskWithNullThrowsException() {
		ContainerTask container = new ContainerTask("Parent", "Has subtasks", TaskPriority.LOW);
		assertThrows(IllegalArgumentException.class, () -> container.addTask(null));
	}
}
