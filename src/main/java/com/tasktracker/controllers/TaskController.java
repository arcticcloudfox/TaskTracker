package com.tasktracker.controllers;


import com.tasktracker.models.Task;
import com.tasktracker.models.User;
import com.tasktracker.models.data.UserRepository;
import com.tasktracker.security.SecurityConfig;
import com.tasktracker.service.TaskServiceMethods;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/")
public class TaskController {

    @Autowired
     private TaskServiceMethods taskService;

    @Autowired
     private UserRepository userRepository;

    public TaskController(TaskServiceMethods taskService) {

        this.taskService = taskService;
    }

    //retrieves tasks by id
    @GetMapping("/tasks/{id}")
    public String getTaskById(@PathVariable int id, Model model) {
        Task task = (Task) taskService.findTaskById(id);
        model.addAttribute("task", task);
        return "details";
    }

    //shows the form to add a task
    @GetMapping("/tasks/add")
    public String showAddForm(Model model) {
        model.addAttribute("task", new Task());
        return "add";
    }

    //gets tasks by specific user
    @GetMapping
    public String getAllTasksByUser(Model model) {
        List<Task> tasks = taskService.getTasksByUser();
        model.addAttribute("tasks", tasks);
        return "tasks";
    }

    //enables user to add a task
    @PostMapping
    public String addTask(@ModelAttribute Task task) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentPrincipalName = authentication.getName();

        User user = userRepository.findByUsername(currentPrincipalName).orElse(null);
        if (user == null) {
            throw new UsernameNotFoundException("User not found: " + currentPrincipalName);
        }

        task.setUser(user);

        taskService.saveTask(task);

        return "redirect:/" + task.getId();
    }

    //allows user to update existing tasks
    @PostMapping("/task/update/{id}")
    public String updateTask(@PathVariable int id, @ModelAttribute("task") Task taskDetails) {
        Task task = (Task) taskService.findTaskById(id);
        task.setTaskName(taskDetails.getTaskName());
        task.setDescription(taskDetails.getDescription());
        task.setCompleted(taskDetails.isCompleted());
        taskService.saveTask(task);
        return "redirect:/";
    }

    //allows user to delete tasks by their id
    @PostMapping("/task/{id}/delete")
    public String deleteTask(@PathVariable int id) {
        taskService.deleteTask(id);
        return "redirect:/";
    }

    //shows the edit task form
    @GetMapping("task/{id}/edit")
    public String showEditForm(@PathVariable int id, Model model) {
        Task task = (Task) taskService.findTaskById(id);
        model.addAttribute("task", task);
        return "edit";
    }

    private User getCurrentUser() {
        String username = ((UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUsername();
        User user = new User();
        user.setUsername(username);
        return user;
    }

}
