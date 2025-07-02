package com.tasktracker.service;

import com.tasktracker.models.User;
import com.tasktracker.models.data.UserRepository;
import com.tasktracker.security.UserDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class AppUserDetailsService {

    @Autowired
    private UserRepository userRepository;

    public org.springframework.security.core.userdetails.UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username).orElse(null);

        if(user == null) {
            throw new UsernameNotFoundException("Could not find user");
        }
        UserDetails userDetails = new UserDetails(user);
        return userDetails;
    }
}
