package edu.westga.cs1302.task_tracker.model.descending;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

import edu.westga.cs1302.task_tracker.model.Descending;
import edu.westga.cs1302.task_tracker.model.Task;

class TestCompare {

    @Test
    void testHighBeforeLow() {
        Task high = new Task("High", "d", Task.TaskPriority.HIGH);
        Task low = new Task("Low", "d", Task.TaskPriority.LOW);
        Descending desc = new Descending();
        assertTrue(desc.compare(high, low) < 0);
    }

    @Test
    void testLowAfterHigh() {
        Task high = new Task("High", "d", Task.TaskPriority.HIGH);
        Task low = new Task("Low", "d", Task.TaskPriority.LOW);
        Descending desc = new Descending();
        assertTrue(desc.compare(low, high) > 0);
    }

    @Test
    void testEqualPriority() {
        Task t1 = new Task("A", "d", Task.TaskPriority.MEDIUM);
        Task t2 = new Task("B", "d", Task.TaskPriority.MEDIUM);
        Descending desc = new Descending();
        assertEquals(0, desc.compare(t1, t2));
    }

    @Test
    void testNullThrows() {
        Descending desc = new Descending();
        Task t = new Task("A", "d", Task.TaskPriority.HIGH);
        assertThrows(IllegalArgumentException.class, () -> desc.compare(null, t));
        assertThrows(IllegalArgumentException.class, () -> desc.compare(t, null));
    }
}
