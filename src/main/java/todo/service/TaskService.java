package todo.service;

import java.util.List;

import todo.model.Task;

public interface TaskService {
    List<Task> getTasks();
    Task createTask(Task task);
}
