package com.personal.todo.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TaskTest {

    @Test
    public void testTaskModel() {
        Task task = new Task("Clean up kitchen", "Throw away leftovers, wash dishes", false);

        assertEquals("Clean up kitchen", task.getTitle());
        assertEquals("Throw away leftovers, wash dishes", task.getDescription());
        assertEquals(false, task.isCompleted());

        task.setTitle("Clean up kitchen thoroughly");
        task.setDescription("Throw away leftovers, wash dishes, wipe kitchen counters");
        task.setCompleted(true);

        assertEquals("Clean up kitchen thoroughly", task.getTitle());
        assertEquals("Throw away leftovers, wash dishes, wipe kitchen counters", task.getDescription());
        assertEquals(true, task.isCompleted());
    }
}
