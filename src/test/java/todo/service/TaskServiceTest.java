package todo.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import todo.model.Task;
import todo.repository.TaskRepository;

@ExtendWith(MockitoExtension.class)
public class TaskServiceTest {

    private TaskService taskService;

    @Mock
    private TaskRepository taskRepository;

    @BeforeEach
    public void setUp() {
        taskService = new TaskServiceImpl(taskRepository);
    }

    @Test
    void testCreateTask_shouldReturnEqual() {
        Task mockTask = new Task("Buy groceries", "3 tomatoes, 2 potatoes", false);

        when(taskRepository.save(mockTask)).thenReturn(mockTask);

        Task task = taskService.createTask(mockTask);

        assertEquals("Buy groceries", task.getTitle());
        assertEquals("3 tomatoes, 2 potatoes", task.getDescription());
        assertEquals(false, task.isCompleted());
    }

    @Test
    void testGetTasks_shouldReturn0Task() {
        List<Task> mockTasks = Arrays.asList();

        when(taskRepository.findAll()).thenReturn(mockTasks);

        List<Task> tasks = taskService.getTasks();
        assertEquals(0, tasks.size());
    }

    @Test
    void testGetTasks_shouldReturn1TaskAndEqual() {
        List<Task> mockTasks = Arrays.asList(
                new Task("Buy groceries", "3 tomatoes, 2 potatoes", false));

        when(taskRepository.findAll()).thenReturn(mockTasks);

        List<Task> tasks = taskService.getTasks();
        assertEquals(1, tasks.size());

        assertEquals("Buy groceries", tasks.get(0).getTitle());
        assertEquals("3 tomatoes, 2 potatoes", tasks.get(0).getDescription());
        assertEquals(false, tasks.get(0).isCompleted());
    }

    @Test
    void testGetTasks_shouldReturn2TasksAndEqual() {
        List<Task> mockTasks = Arrays.asList(
                new Task("Buy groceries", "3 tomatoes, 2 potatoes", false),
                new Task("Clean up kitchen", "wash dishes", true));

        when(taskRepository.findAll()).thenReturn(mockTasks);

        List<Task> tasks = taskService.getTasks();
        assertEquals(2, tasks.size());

        assertEquals("Buy groceries", tasks.get(0).getTitle());
        assertEquals("3 tomatoes, 2 potatoes", tasks.get(0).getDescription());
        assertEquals(false, tasks.get(0).isCompleted());

        assertEquals("Clean up kitchen", tasks.get(1).getTitle());
        assertEquals("wash dishes", tasks.get(1).getDescription());
        assertEquals(true, tasks.get(1).isCompleted());
    }

}
