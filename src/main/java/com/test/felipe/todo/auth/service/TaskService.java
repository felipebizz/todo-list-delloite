package com.test.felipe.todo.auth.service;

import com.test.felipe.todo.auth.model.Task;

import java.util.List;

public interface TaskService {

    void save(Task task);

    Task findById(final int id);

    void remove(Task task);

    Task findByDescription(String description);

    List<Task> findAllByUserId(final Long id);

}
