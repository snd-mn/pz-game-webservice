package org.projectzion.game.controllers;

import org.projectzion.game.persitence.entities.security.User;
import org.projectzion.game.services.UserService;
import org.projectzion.game.tos.UserTO;
import org.projectzion.game.utils.converter.User2UserTOConverter;
import org.projectzion.game.tos.filters.UserTOExportFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


import javax.transaction.Transactional;


@RestController
public class UserController {

    @Autowired
    UserService userService;

    @Autowired
    User2UserTOConverter user2UserTOConverter;

    @Autowired
    UserTOExportFilter userTOExportFilter;

    @PostMapping("user")
    @Transactional
    public UserTO postUser(@RequestBody UserTO userTO){
        User createdUser = this.userService.createUser(userTO);
        UserTO userTOResponse =user2UserTOConverter.convert(createdUser);
        return userTOExportFilter.convert(userTOResponse);
    }

    @PutMapping("user")
    @Transactional
    public UserTO putUser(@RequestBody UserTO userTO){
        User userBack = this.userService.updateUser(userTO);
        UserTO userTOResponse = user2UserTOConverter.convert(userBack);
        return userTOExportFilter.convert(userTOResponse);
    }

    @GetMapping("user/{id}")
    @Transactional
    public UserTO getUser(@PathVariable("id") Long id){
        User userBack = this.userService.findById(id);
        UserTO userTOResponse = user2UserTOConverter.convert(userBack);
        return userTOExportFilter.convert(userTOResponse);
    }

    @DeleteMapping("user/{id}")
    @Transactional
    public void deleteUser(@PathVariable("id") Long id){
        this.userService.deleteUser(id);
    }
}
