package com.personal.todo.service;

import java.util.List;

import com.personal.todo.model.Task;

public interface TaskService {
    List<Task> getTasks();
    Task createTask(Task task);
}
