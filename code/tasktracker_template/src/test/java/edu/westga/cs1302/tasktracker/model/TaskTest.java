package edu.westga.cs1302.tasktracker.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

/**
 * Unit tests for Task class.  
 * Tests getter and setter functionality for Task objects.
 * 
 * @author NoahG
 * @version Fall 2025
 */
public class TaskTest {

    @Test
    public void testTaskConstructorAndGetters() {
        Task task = new Task("Test Task", "Description", 2);
        assertEquals("Test Task", task.getName());
        assertEquals("Description", task.getDescription());
        assertEquals(2, task.getPriority());
    }

    @Test
    public void testSetNameAndSetDescriptionAndSetPriority() {
        Task task = new Task("Old Name", "Old Desc", 1);
        task.setName("New Name");
        task.setDescription("New Desc");
        task.setPriority(5);
        assertEquals("New Name", task.getName());
        assertEquals("New Desc", task.getDescription());
        assertEquals(5, task.getPriority());
    }
}
