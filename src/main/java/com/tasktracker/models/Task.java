package com.tasktracker.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Task {

    //these are values that save to the database and allows for the application to retrieve them
    @Id
    @GeneratedValue
    private int id;

    private String taskName;

    private String description;

    private boolean completed;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    public void setUser(User user) {
    }

    public User getUser() {
        return user;
    }
}
