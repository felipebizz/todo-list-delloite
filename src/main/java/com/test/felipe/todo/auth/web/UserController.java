package com.test.felipe.todo.auth.web;

import com.test.felipe.todo.auth.login.User;
import com.test.felipe.todo.auth.model.Task;
import com.test.felipe.todo.auth.service.SecurityService;
import com.test.felipe.todo.auth.service.TaskService;
import com.test.felipe.todo.auth.service.UserService;
import com.test.felipe.todo.auth.validator.TaskValidator;
import com.test.felipe.todo.auth.validator.UserValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.Date;

@Controller
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private SecurityService securityService;

    @Autowired
    private TaskService taskService;

    @Autowired
    private UserValidator userValidator;

    @Autowired
    private TaskValidator taskValidator;

    @RequestMapping(value = "/registration", method = RequestMethod.GET)
    public String registration(Model model) {
        model.addAttribute("userForm", new User());

        return "registration";
    }

    @RequestMapping(value = "/registration", method = RequestMethod.POST)
    public String saveUser(@ModelAttribute("userForm") User userForm, BindingResult bindingResult, Model model) {
        userValidator.validate(userForm, bindingResult);

        if (bindingResult.hasErrors()) {
            return "registration";
        }

        userService.save(userForm);

        securityService.autologin(userForm.getUsername(), userForm.getPasswordConfirm());

        return "redirect:/welcome";
    }

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String login(Model model, String error, String logout) {
        if (error != null)
            model.addAttribute("error", "Your username and password is invalid.");

        if (logout != null)
            model.addAttribute("message", "You have been logged out successfully.");

        return "login";
    }

    @RequestMapping(value = {"/", "/welcome"}, method = RequestMethod.GET)
    public String welcome(Model model) {
        model.addAttribute("todoList", taskService.findAllByUserId(getIDUserLogged()));
        return "welcome";
    }

    @RequestMapping(value = "/new", method = RequestMethod.GET)
    public String task(Model model) {
        model.addAttribute("taskForm", new Task());
        return "task";
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public String newTask(@ModelAttribute("taskForm") Task taskForm, BindingResult bindingResult,
                          Error error, Model model) {

        taskValidator.validate(taskForm, bindingResult);

        if (bindingResult.hasErrors()) {
            return "task";
        }

        taskService.save(new Task(taskForm.getDescription(), getIDUserLogged()));
        return "redirect:/welcome";
    }

    @RequestMapping(value = "/update", method = RequestMethod.PUT)
    public String update(@ModelAttribute("taskForm") Task taskForm, BindingResult bindingResult) {

        taskValidator.validate(taskForm, bindingResult);

        if (bindingResult.hasErrors()) {
            return "task";
        }

        Task task = taskService.findById(taskForm.getId());
        task.setDescription(taskForm.getDescription());
        task.setCompleted(taskForm.isCompleted());
        task.setUpdatedAt(new Date());
        taskService.save(task);
        return "redirect:/welcome";
    }

    @RequestMapping(value = "/edit/{id}", method = RequestMethod.POST)
    public String editTask(@ModelAttribute("taskForm") Task taskForm, @PathVariable int id, Model model) {

        Task taskReturned = taskService.findById(id);
        model.addAttribute("taskForm", taskReturned);
        return "task";
    }

    @RequestMapping(value = "/remove/{id}", method = RequestMethod.POST)
    public String removeTask(@ModelAttribute("taskForm") Task taskForm, @PathVariable int id) {

        Task taskReturned = taskService.findById(id);
        taskService.remove(taskReturned);
        return "redirect:/welcome";
    }

    /**
     * Get Id User Logged by SecurityContextHolder:
     *
     * @return id User Logged
     */
    private long getIDUserLogged() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (!(authentication instanceof AnonymousAuthenticationToken)) {
            String currentUserName = authentication.getName();
            User byUsername = userService.findByUsername(currentUserName);
            return byUsername.getId();
        }
        throw new RuntimeException("User is not Logged");
    }


}