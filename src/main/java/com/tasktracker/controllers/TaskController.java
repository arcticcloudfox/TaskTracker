package com.tasktracker.controllers;


import com.tasktracker.models.Task;
import com.tasktracker.models.User;
import com.tasktracker.models.data.TaskRepository;
import com.tasktracker.models.data.UserRepository;
import com.tasktracker.security.SecurityConfig;
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
@RequestMapping("/task")
public class TaskController {

    @Autowired
    private TaskRepository taskRepository;

    @Autowired
     private UserRepository userRepository;

    //retrieves tasks by id
    @GetMapping("/{id}")
    public String getTaskById(@PathVariable int id, Model model) {
        Task task = (Task) taskRepository.findTaskById(id);
        model.addAttribute("task", task);
        return "view";
    }

    //shows the form to add a task
    @GetMapping("/add")
    public String showAddForm(Model model) {
        model.addAttribute("task", new Task());
        return "add";
    }

    //gets tasks by specific user
    @GetMapping("")
    public String getAllTasksByUser(Model model) {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();

        User user = userRepository.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException("User not found: " + username));

        List<Task> tasks = taskRepository.findByUser(user);
        model.addAttribute("tasks", tasks);
        return "index";
    }

    //enables user to add a task
    @PostMapping("/add")
    public String addTask(@ModelAttribute Task task) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentPrincipalName = authentication.getName();

        User user = userRepository.findByUsername(currentPrincipalName).orElse(null);
        if (user == null) {
            throw new UsernameNotFoundException("User not found: " + currentPrincipalName);
        }

        task.setUser(user);

        System.out.println("Task saved: " + task.getTaskName() + ", user: " + user.getUsername());

        taskRepository.save(task);

        return "redirect:/task";
    }

    //allows user to update existing tasks
    @PostMapping("/update/{id}")
    public String updateTask(@PathVariable int id, @ModelAttribute("task") Task taskDetails) {
        Task task = (Task) taskRepository.findTaskById(id);
        task.setTaskName(taskDetails.getTaskName());
        task.setDescription(taskDetails.getDescription());
        task.setCompleted(taskDetails.isCompleted());
        taskRepository.save(task);
        return "redirect:/task";
    }

    //allows user to delete tasks by their id
    @PostMapping("/{id}/delete")
    public String deleteTask(@PathVariable int id) {
        taskRepository.deleteById(id);
        return "redirect:/task";
    }

    //shows the edit task form
    @GetMapping("/{id}/edit")
    public String showEditForm(@PathVariable int id, Model model) {
        Task task = (Task) taskRepository.findTaskById(id);
        model.addAttribute("task", task);
        return "edit";
    }

    @PostMapping("/{id}/completedTask")
    public String toggleCompletedForm(@PathVariable int id, @RequestParam boolean completed) {
        Task task = taskRepository.findTaskById(id);
        task.setCompleted(completed);
        taskRepository.save(task);
        return "redirect:/task";
    }

    private User getCurrentUser() {
        String username = ((UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUsername();
        return userRepository.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException("User not found: " + username));
    }

}
