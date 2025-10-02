package edu.westga.cs1302.tasktracker.model;

/**
 * Represents a Task with a name, description, and priority.
 * 
 * @author NoahG
 * @version Fall 2025
 */
public class Task {
    private String name;
    private String description;
    private int priority;

    /**
     * Constructs a Task with the specified name, description, and priority.
     * 
     * @param name        the name of the task
     * @param description the description of the task
     * @param priority    the priority of the task
     */
    public Task(String name, String description, int priority) {
        this.name = name;
        this.description = description;
        this.priority = priority;
    }

    /**
     * Returns the task name.
     * 
     * @return the name
     */
    public String getName() {
        return this.name;
    }

    /**
     * Sets the task name.
     * 
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Returns the task description.
     * 
     * @return the description
     */
    public String getDescription() {
        return this.description;
    }

    /**
     * Sets the task description.
     * 
     * @param description the description to set
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Returns the priority of the task.
     * 
     * @return the priority
     */
    public int getPriority() {
        return this.priority;
    }

    /**
     * Sets the task priority.
     * 
     * @param priority the priority to set
     */
    public void setPriority(int priority) {
        this.priority = priority;
    }
}
