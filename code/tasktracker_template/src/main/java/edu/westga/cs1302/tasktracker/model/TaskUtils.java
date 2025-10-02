package edu.westga.cs1302.tasktracker.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Utility methods for working with tasks.
 * 
 * @author NoahG
 * @version Fall 2025
 */
public class TaskUtils {

    /**
     * Filters a list of tasks by priority.
     * 
     * @param tasks    the list of tasks
     * @param priority the priority to filter by
     * @return a list of tasks with the given priority
     */
    public static List<Task> filterByPriority(List<Task> tasks, int priority) {
        List<Task> result = new ArrayList<>();
        for (Task task : tasks) {
            if (task.getPriority() == priority) {
                result.add(task);
            }
        }
        return result;
    }

    /**
     * Counts the number of tasks with a given priority.
     * 
     * @param tasks    the list of tasks
     * @param priority the priority to count
     * @return the number of tasks with the specified priority
     */
    public static int countByPriority(List<Task> tasks, int priority) {
        int count = 0;
        for (Task task : tasks) {
            if (task.getPriority() == priority) {
                count++;
            }
        }
        return count;
    }
}
