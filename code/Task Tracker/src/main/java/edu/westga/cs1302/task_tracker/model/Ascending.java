package edu.westga.cs1302.task_tracker.model;

import java.util.Comparator;

/**
 * Compares tasks in ascending order of priority
 * 
 * @author Noah
 * @version Fall 2025
 */
public class Ascending implements Comparator<Task> {

    @Override
    public int compare(Task first, Task second) {
        if (first == null || second == null) {
            throw new IllegalArgumentException("Tasks cannot be null");
        }
        return -first.getPriority().compareTo(second.getPriority());
    }
}

