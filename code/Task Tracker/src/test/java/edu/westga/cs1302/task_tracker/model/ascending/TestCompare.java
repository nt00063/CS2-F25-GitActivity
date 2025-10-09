package edu.westga.cs1302.task_tracker.model.ascending;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

import edu.westga.cs1302.task_tracker.model.Ascending;
import edu.westga.cs1302.task_tracker.model.Task;

class TestCompare {

    @Test
    void testLowBeforeHigh() {
        Task low = new Task("Low", "d", Task.TaskPriority.LOW);
        Task high = new Task("High", "d", Task.TaskPriority.HIGH);
        Ascending asc = new Ascending();
        assertTrue(asc.compare(low, high) < 0);
    }

    @Test
    void testHighAfterLow() {
        Task low = new Task("Low", "d", Task.TaskPriority.LOW);
        Task high = new Task("High", "d", Task.TaskPriority.HIGH);
        Ascending asc = new Ascending();
        assertTrue(asc.compare(high, low) > 0);
    }

    @Test
    void testEqualPriority() {
        Task t1 = new Task("A", "d", Task.TaskPriority.MEDIUM);
        Task t2 = new Task("B", "d", Task.TaskPriority.MEDIUM);
        Ascending asc = new Ascending();
        assertEquals(0, asc.compare(t1, t2));
    }

    @Test
    void testNullThrows() {
        Ascending asc = new Ascending();
        Task t = new Task("A", "d", Task.TaskPriority.LOW);
        assertThrows(IllegalArgumentException.class, () -> asc.compare(null, t));
        assertThrows(IllegalArgumentException.class, () -> asc.compare(t, null));
    }
}
