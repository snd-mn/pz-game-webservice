package com.example.restservice.services;

import com.example.restservice.persitence.entities.User;
import com.example.restservice.persitence.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    public User createUser(User user){
        User userBack = this.userRepository.save(user);
        return userBack;
    }

    public Optional<User> getUserById(Long id){
        return this.userRepository.findById(id);
    }
}
