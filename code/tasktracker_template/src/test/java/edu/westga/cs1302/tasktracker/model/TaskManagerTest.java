package edu.westga.cs1302.tasktracker.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import java.util.List;
import java.util.ArrayList;
import org.junit.jupiter.api.Test;

/**
 * Tests for task management functionality using TaskUtils.
 * Replaces the original TaskManagerTest as TaskManager does not exist.
 * 
 * @author NoahG
 * @version Fall 2025
 */
public class TaskManagerTest {

    @Test
    public void testFilterByPriority() {
        List<Task> tasks = new ArrayList<>();
        tasks.add(new Task("Task1", "Desc", 1));
        tasks.add(new Task("Task2", "Desc", 2));
        tasks.add(new Task("Task3", "Desc", 1));

        List<Task> filtered = TaskUtils.filterByPriority(tasks, 1);
        assertEquals(2, filtered.size());
    }

    @Test
    public void testCountByPriority() {
        List<Task> tasks = new ArrayList<>();
        tasks.add(new Task("Task1", "Desc", 1));
        tasks.add(new Task("Task2", "Desc", 2));
        tasks.add(new Task("Task3", "Desc", 1));

        int count = TaskUtils.countByPriority(tasks, 1);
        assertEquals(2, count);
    }
}
