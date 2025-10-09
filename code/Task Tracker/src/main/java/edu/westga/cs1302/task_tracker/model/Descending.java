package edu.westga.cs1302.task_tracker.model;

import java.util.Comparator;

/**
 * Compares tasks in descending order of priority (HIGH → MEDIUM → LOW).
 * Higher priority comes first.
 * 
 * @author Noah Toups
 * @version Fall 2025
 */
public class Descending implements Comparator<Task> {

    @Override
    public int compare(Task first, Task second) {
        if (first == null || second == null) {
            throw new IllegalArgumentException("Tasks cannot be null");
        }

        return second.getPriority().compareTo(first.getPriority());
    }
}
