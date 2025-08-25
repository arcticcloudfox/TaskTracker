package com.tasktracker.models.data;

import com.tasktracker.models.Task;
import com.tasktracker.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TaskRepository extends JpaRepository<Task, Integer> {
    List<Task> findByUserUsername(String username);
    List<Task> findAll();
    List<Task> findByUser(User user);
    Task findTaskById(int id);
    void deleteById(int id);

}
