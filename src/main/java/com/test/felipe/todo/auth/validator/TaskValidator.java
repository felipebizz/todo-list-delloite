package com.test.felipe.todo.auth.validator;

import com.test.felipe.todo.auth.model.Task;
import com.test.felipe.todo.auth.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

@Component
public class TaskValidator implements Validator {
    @Autowired
    private TaskService taskService;

    @Override
    public boolean supports(Class<?> aClass) {
        return Task.class.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        Task task = (Task) o;

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "description", "NotEmpty");
        if (task.getDescription().length() < 1) {
            errors.rejectValue("description", "NotEmpty");
        }
        Task foundTask = taskService.findByDescription(task.getDescription());

        if (foundTask != null && (task.getId() != foundTask.getId())) {
            errors.rejectValue("description", "Duplicate.taskForm.description");
        }

    }
}