package com.tasktracker.models.data;

import com.tasktracker.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Integer> {
    Optional<User> findByUsername(String username);
    User saveUser(User user);
    void deleteUser(int userId);

    boolean isUsernameTaken(String username);
}
