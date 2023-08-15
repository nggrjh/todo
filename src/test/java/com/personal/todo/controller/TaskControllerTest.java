package com.personal.todo.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.personal.todo.model.Task;
import com.personal.todo.service.TaskService;

@ExtendWith(MockitoExtension.class)
public class TaskControllerTest {

    private TaskController taskController;

    @Mock
    private TaskService taskService;

    @BeforeEach
    public void setUp() {
        taskController = new TaskController(taskService);
    }

    @Test
    void testCreateTask() {

    }

    @Test
    void testGetTasks_shouldReturn200OKAndEqual() {
        List<Task> mockTasks = Arrays.asList(
                new Task("Buy groceries", "3 tomatoes, 2 potatoes", false),
                new Task("Clean up kitchen", "wash dishes", true));

        when(taskService.getTasks()).thenReturn(mockTasks);

        ResponseEntity<List<Task>> response = taskController.getTasks();
        assertEquals(HttpStatus.OK, response.getStatusCode());

        List<Task> tasks = response.getBody();

        assertEquals("Buy groceries", tasks.get(0).getTitle());
        assertEquals("3 tomatoes, 2 potatoes", tasks.get(0).getDescription());
        assertEquals(false, tasks.get(0).isCompleted());

        assertEquals("Clean up kitchen", tasks.get(1).getTitle());
        assertEquals("wash dishes", tasks.get(1).getDescription());
        assertEquals(true, tasks.get(1).isCompleted());

    }

}
