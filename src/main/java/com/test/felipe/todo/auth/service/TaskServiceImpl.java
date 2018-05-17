package com.test.felipe.todo.auth.service;

import com.test.felipe.todo.auth.model.Task;
import com.test.felipe.todo.auth.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TaskServiceImpl implements TaskService {


    @Autowired
    private TaskRepository taskRepository;

    @Override
    public void save(Task task) {
        taskRepository.save(task);
    }

    @Override
    public Task findById(final int id) {
        return taskRepository.findById(id);
    }

    @Override
    public List<Task> findAllByUserId(Long id) {
        return taskRepository.findAllByUserId(id);
    }

    @Override
    public void remove(Task task) {
        taskRepository.delete(task);
    }

    @Override
    public Task findByDescription(String description) {
        return taskRepository.findByDescription(description);
    }
}
