package com.personal.todo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.personal.todo.model.Task;

public interface TaskRepository extends JpaRepository<Task, Long> {

}
