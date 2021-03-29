package com.example.restservice.controllers;

import com.example.restservice.persitence.entities.User;
import com.example.restservice.services.UserService;
import com.example.restservice.utils.Gps;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class UserController {

    @Autowired
    UserService userService;

    @PutMapping("user/put")
    public User createUser(@RequestBody User user){
        User userBack = this.userService.createUser(user);
        return userBack;
    }



}
