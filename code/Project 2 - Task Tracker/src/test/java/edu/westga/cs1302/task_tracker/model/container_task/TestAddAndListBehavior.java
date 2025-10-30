package edu.westga.cs1302.task_tracker.model.container_task;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.Test;

import edu.westga.cs1302.task_tracker.model.ContainerTask;
import edu.westga.cs1302.task_tracker.model.Task;
import edu.westga.cs1302.task_tracker.model.Task.TaskPriority;

/** Tests ContainerTask add/return semantics and list immutability. */
class TestAddAndListBehavior {

	@Test
	void testAddTaskReturnsThis() {
		ContainerTask ct = new ContainerTask("P", "", TaskPriority.MEDIUM);
		Task c = new Task("C", "", TaskPriority.LOW);
		Task returned = ct.addTask(c);
		assertSame(ct, returned);
	}

	@Test
	void testGetSubTasksIsUnmodifiable() {
		ContainerTask ct = new ContainerTask("P", "", TaskPriority.MEDIUM);
		Task c = new Task("C", "", TaskPriority.LOW);
		ct.addTask(c);
		List<Task> subs = ct.getSubTasks();
		assertEquals(1, subs.size());
		assertThrows(UnsupportedOperationException.class, () -> subs.add(c));
	}
}
