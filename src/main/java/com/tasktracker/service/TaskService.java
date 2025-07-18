package com.tasktracker.service;

import com.tasktracker.models.Task;

import java.util.List;

public interface TaskService {
    List<Task> findAllTasks();
    Task saveTask(Task task);
    List<Task> getTasksByUser();
    Task findTaskById(int id);
    void deleteTask(int id);

}
