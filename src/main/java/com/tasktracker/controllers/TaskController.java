package com.tasktracker.controllers;


import com.tasktracker.models.Task;
import com.tasktracker.models.User;
import com.tasktracker.models.data.UserRepository;
import com.tasktracker.security.SecurityConfig;
import com.tasktracker.service.TaskServiceMethods;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/")
public class TaskController {

    //redo this to better fit the creation of the task services files
    //also to better fit the templates
    @Autowired
     private TaskServiceMethods taskService;

    @Autowired
     private UserRepository userRepository;

    public TaskController(TaskServiceMethods taskService) {
        this.taskService = taskService;
    }

    @GetMapping("/tasks/{id}")
    public String getTaskById(@PathVariable int id, Model model) {
        Task task = (Task) taskService.findTaskById(id);
        model.addAttribute("task", task);
        return "details";
    }

    @GetMapping("/tasks/add")
    public String showAddForm(Model model) {
        model.addAttribute("task", new Task());
        return "add";
    }

    public String getAllTasksByUser(Model model) {
        List<Task> tasks = taskService.getTasksByUser();
        model.addAttribute("tasks", tasks);
        return "tasks";
    }

    public String addTask(@ModelAttribute Task task) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentPrincipalName = authentication.getName();

        User user = userRepository(currentPrincipalName).orElse(null);
        assert user != null;
        Integer userId = user.getId();

        taskService.saveTask(task);

        return "redirect:/" + task.getId();
    }

    //Finish methods
    //figure out the errors







//    @Autowired
//    private TaskRepository taskRepository;
//
//    //This returns the list of tasks that user has created.
//    @GetMapping("/tasks")
//    public List<Tasks> getTasks() {
//        return (List<Tasks>) taskRepository.findAll();
//    }
//
//    //allows user to add tasks
//    @PostMapping("/tasks/add")
//    public Tasks addTasks(@RequestBody Tasks tasks){
//        return taskRepository.save(tasks);
//    }
//
//    //allows user to update tasks
//    @PutMapping("/tasks/update/{id}")
//    public Tasks updateTask(@PathVariable int id, @RequestBody Tasks updatedTask) {
//        Optional<Tasks> oldtask = taskRepository.findById(id);
//        if (oldtask.isPresent()) {
//            Tasks taskToUpdate = oldtask.get();
//            if (updatedTask.getTaskName() != null && !updatedTask.getTaskName().isEmpty()) {
//                taskToUpdate.setTaskName(updatedTask.getTaskName());
//            }
//            if (updatedTask.getDescription() != null && !updatedTask.getDescription().isEmpty()) {
//                taskToUpdate.setDescription(updatedTask.getDescription());
//            }
//            return taskRepository.save(taskToUpdate);
//        } else {
//            return null;
//        }
//    }
//
//    //allows users to remove tasks
//    @DeleteMapping("/tasks/delete/{deleteTask}")
//    public Tasks deleteTaskById(@PathVariable int deleteTask) {
//        Optional<Tasks> taskToDelete = taskRepository.findById(deleteTask);
//        if (taskToDelete.isPresent()) {
//            taskRepository.deleteById(deleteTask);
//        }
//        return null;
//    }
}
