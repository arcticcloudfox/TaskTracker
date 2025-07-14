package com.tasktracker.service;

import org.springframework.scheduling.config.Task;

import java.util.List;

public interface TaskService {
    List<Task> findAllTasks();
    Task saveTask(Task task);
    List<Task> getTasksForCurrentUser();
    Task findTaskById(int id);
    void deleteTask(int id);

}
