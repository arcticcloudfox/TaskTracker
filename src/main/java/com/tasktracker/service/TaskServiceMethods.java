package com.tasktracker.service;

import com.tasktracker.models.Task;
import com.tasktracker.models.User;
import com.tasktracker.models.data.TaskRepository;
import com.tasktracker.models.data.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TaskServiceMethods implements TaskService {

    @Autowired
    private TaskRepository taskRepository;

    @Autowired
    private UserRepository userRepository;

    @Override
    public List<Task> findAllTasks() {
        return taskRepository.findAll();
    }

    public Task saveTask(Task task) {
        String  username = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = userRepository.findByUsername(username).orElseThrow(() -> new IllegalArgumentException("User not found"));
        task.setUser(user);
        return taskRepository.save(task);
    }

    public List<Task> getTasksByUser() {
        String  username = SecurityContextHolder.getContext().getAuthentication().getName();
        return taskRepository.findByUserUsername(username);
    }

    @Override
    public Task findTaskById(int id) {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        Task task = taskRepository.findById(id).orElse(null);
        if (task != null && task.getUser().getUsername().equals(username)) {
            return task;
        }
        return null;
    }

    public void deleteTask(int id) {
        String  username = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = userRepository.findByUsername(username).orElseThrow();
        Task task = taskRepository.findById(id).orElseThrow();

        if (task.getUser().equals(user)) {
            taskRepository.deleteById(id);
        }
    }
}
