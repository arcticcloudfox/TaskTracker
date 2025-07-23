package com.tasktracker.models;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.scheduling.config.Task;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@Table(name = "users")
@Getter
@Setter
public class User {

    //puts user details into database and retrieves the information
    @Id
    @GeneratedValue
    private int id;

    private String username;
    private String password;

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }
}
