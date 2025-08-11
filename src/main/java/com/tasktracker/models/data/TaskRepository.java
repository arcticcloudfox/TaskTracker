package com.tasktracker.models.data;

import com.tasktracker.models.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TaskRepository extends JpaRepository<Task, Integer> {
    List<Task> findByUserUsername(String username);
    List<Task> findAllTasks();
    Task saveTask(Task task);
    List<Task> getTasksByUser();
    Task findTaskById(int id);
    void deleteTask(int id);

}
