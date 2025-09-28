package edu.westga.cs1302.tasktracker.model;

public class Task {

    private final String name;       // immutable
    private String description;      // editable
    private final String priority;   // immutable

    // Constructor
    public Task(String name, String description, String priority) {
        if (name == null || name.isEmpty()) {
            throw new IllegalArgumentException("Name cannot be null or empty");
        }
        if (priority == null || priority.isEmpty()) {
            throw new IllegalArgumentException("Priority cannot be null or empty");
        }
        this.name = name;
        this.description = description;
        this.priority = priority;
    }

    // Getters
    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public String getPriority() {
        return priority;
    }

    // Update description
    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return name;
    }
}
