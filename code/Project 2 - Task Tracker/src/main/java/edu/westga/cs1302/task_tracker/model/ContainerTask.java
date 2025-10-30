package edu.westga.cs1302.task_tracker.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/** Represents a Task that can contain subtasks.
 * 
 * @author CS 1302
 * @version Fall 2025
 */
public class ContainerTask extends Task {

	private final List<Task> subtasks;

	/** Create a new ContainerTask.
	 * 
	 * @precondition name != null && !name.isEmpty() &&
	 *               description != null &&
	 *               priority != null
	 * @postcondition none
	 * 
	 * @param name the name of the task
	 * @param description the description of the task
	 * @param priority the priority of the task
	 */
	public ContainerTask(String name, String description, TaskPriority priority) {
		super(name, description, priority);
		this.subtasks = new ArrayList<>();
	}

	/** Adds the provided subtask to this container task.
	 * 
	 * @precondition subtask != null
	 * @postcondition the provided subtask is added to this container’s list of subtasks
	 * 
	 * @param subtask the subtask to add
	 * @return this ContainerTask (after the subtask has been added)
	 */
	@Override
	public Task addTask(Task subtask) {
		if (subtask == null) {
			throw new IllegalArgumentException("subtask must not be null");
		}
		this.subtasks.add(subtask);
		return this;
	}

	/** Returns the list of subtasks contained in this task.
	 * 
	 * @precondition none
	 * @postcondition none
	 * 
	 * @return an unmodifiable list of the subtasks
	 */
	@Override
	public List<Task> getSubTasks() {
		return Collections.unmodifiableList(this.subtasks);
	}

	/** Returns a String representation of the task including an indicator that it has subtasks.
	 * 
	 * @precondition none
	 * @postcondition none
	 * 
	 * @return a String showing the task name followed by "(+)"
	 */
	@Override
	public String toString() {
		return this.getName() + " (+)";
	}
}
