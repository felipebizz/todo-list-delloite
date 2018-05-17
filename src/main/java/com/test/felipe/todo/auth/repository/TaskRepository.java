package com.test.felipe.todo.auth.repository;

import com.test.felipe.todo.auth.model.Task;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TaskRepository extends JpaRepository<Task, Long> {

    Task findById(final int id);

    List<Task> findAllByUserId(long id);

    Task findByDescription(String description);
}