package com.tasktracker.controllers;


import com.tasktracker.models.Task;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/")
public class TaskController {

    //redo this to better fit the creation of the task services files
    //also to better fit the templates

    @GetMapping("/task/{id}")
    public String getTaskById(@PathVariable int id, Model model) {
        Task task = (Task) taskService.findTaskById(id);
    }







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
