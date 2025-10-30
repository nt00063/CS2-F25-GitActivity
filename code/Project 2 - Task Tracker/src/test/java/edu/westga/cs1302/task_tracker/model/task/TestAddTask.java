package edu.westga.cs1302.task_tracker.model.task;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.Test;

import edu.westga.cs1302.task_tracker.model.ContainerTask;
import edu.westga.cs1302.task_tracker.model.Task;
import edu.westga.cs1302.task_tracker.model.Task.TaskPriority;

/** Tests the addTask and getSubTasks methods of the Task class.
 * 
 * @author CS 1302
 * @version Fall 2025
 */
class TestAddTask {

	@Test
	void testAddTaskWithNullThrowsException() {
		Task task = new Task("Parent", "Parent task", TaskPriority.HIGH);
		assertThrows(IllegalArgumentException.class, () -> task.addTask(null));
	}

	@Test
	void testAddTaskReturnsContainerTask() {
		Task parent = new Task("Parent", "Parent task", TaskPriority.MEDIUM);
		Task sub = new Task("Child", "Sub task", TaskPriority.LOW);

		Task result = parent.addTask(sub);

		assertTrue(result instanceof ContainerTask);
	}

	@Test
	void testAddedSubtaskIsStored() {
		Task parent = new Task("Parent", "Parent task", TaskPriority.MEDIUM);
		Task sub = new Task("Child", "Sub task", TaskPriority.LOW);

		Task result = parent.addTask(sub);
		List<Task> subtasks = result.getSubTasks();

		assertEquals(1, subtasks.size());
		assertEquals("Child", subtasks.get(0).getName());
	}

	@Test
	void testGetSubTasksReturnsEmptyListForRegularTask() {
		Task simple = new Task("Simple", "No subtasks", TaskPriority.HIGH);
		assertTrue(simple.getSubTasks().isEmpty());
	}
}
