package com.personal.todo.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.parallel.Execution;
import org.junit.jupiter.api.parallel.ExecutionMode;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.personal.todo.model.Task;
import com.personal.todo.repository.TaskRepository;

@ExtendWith(MockitoExtension.class)
@Execution(ExecutionMode.CONCURRENT)
public class TaskServiceTest {
    private TaskService taskService;

    @Mock
    private TaskRepository taskRepository;

    @BeforeEach
    public void setUp() {
        taskService = new TaskService(taskRepository);
    }

    @Test
    void testCreateTask() {

        class Param {
            Task task;

            public Param(Task task) {
                this.task = task;
            }
        }

        class Expected {
            Task task;

            public Expected(Task task) {
                this.task = task;
            }
        }

        class ExpectSave {
            Task task;

            public ExpectSave(Task task) {
                this.task = task;
            }
        }

        class TestCase {
            private Param param;
            private Expected expected;

            private ExpectSave expectSave;

            public TestCase(Param param, Expected expected, ExpectSave expectSave) {
                this.param = param;
                this.expected = expected;
                this.expectSave = expectSave;
            }
        }

        Map<String, TestCase> testCases = new HashMap<>();
        testCases.put("shouldReturnEqual",
                new TestCase(
                        new Param(new Task("Buy groceries", "3 tomatoes, 2 potatoes", false)),
                        new Expected(new Task("Buy groceries", "3 tomatoes, 2 potatoes", false)),
                        new ExpectSave(new Task("Buy groceries", "3 tomatoes, 2 potatoes", false))));

        for (Map.Entry<String, TestCase> entry : testCases.entrySet()) {
            String name = entry.getKey();
            TestCase test = entry.getValue();

            when(taskRepository.save(test.param.task)).thenReturn(test.expectSave.task);

            Task result = taskService.createTask(test.param.task);
            Task expected = test.expected.task;

            assertEquals(expected.getTitle(), result.getTitle(), name);
            assertEquals(expected.getDescription(), result.getDescription(), name);
            assertEquals(expected.isCompleted(), result.isCompleted(), name);
        }

    }

    @Test
    @Execution(ExecutionMode.CONCURRENT)
    void testGetTasks() {

        class Expected {
            int size;
            List<Task> tasks;

            public Expected(int size, List<Task> tasks) {
                this.size = size;
                this.tasks = tasks;
            }
        }

        class ExpectFindAll {
            List<Task> tasks;

            public ExpectFindAll(List<Task> tasks) {
                this.tasks = tasks;
            }
        }

        class TestCase {
            private Expected expected;
            private ExpectFindAll expectFindAll;

            public TestCase(Expected expected, ExpectFindAll expectFindAll) {
                this.expected = expected;
                this.expectFindAll = expectFindAll;

            }
        }

        Map<String, TestCase> testCases = new HashMap<>();
        testCases.put("shouldReturnEmpty",
                new TestCase(
                        new Expected(0, Arrays.asList()),
                        new ExpectFindAll(Arrays.asList())));
        testCases.put("shouldReturnEqual",
                new TestCase(
                        new Expected(1, Arrays.asList(new Task("Buy groceries", "3 tomatoes, 2 potatoes", false))),
                        new ExpectFindAll(Arrays.asList(new Task("Buy groceries", "3 tomatoes, 2 potatoes", false)))));

        for (Map.Entry<String, TestCase> entry : testCases.entrySet()) {
            String name = entry.getKey();
            TestCase test = entry.getValue();

            when(taskRepository.findAll()).thenReturn(test.expectFindAll.tasks);

            List<Task> tasks = taskService.getTasks();
            assertEquals(test.expected.size, tasks.size(), name);

            for (int i = 0; i < test.expected.tasks.size(); i++) {
                Task result = tasks.get(i);
                Task expected = test.expected.tasks.get(i);

                assertEquals(expected.getTitle(), result.getTitle(), name);
                assertEquals(expected.getDescription(), result.getDescription(), name);
                assertEquals(expected.isCompleted(), result.isCompleted(), name);
            }
        }

    }
}
