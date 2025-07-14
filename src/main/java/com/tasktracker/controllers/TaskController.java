package com.tasktracker.controllers;


import com.tasktracker.models.Tasks;
import com.tasktracker.models.data.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/")
public class TaskController {

    //redo this to better fit the creation of the task services files
    //also to better fit the templates







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
