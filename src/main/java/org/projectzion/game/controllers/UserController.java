package org.projectzion.game.controllers;

import org.projectzion.game.persitence.entities.security.User;
import org.projectzion.game.services.UserService;
import org.projectzion.game.tos.UserTO;
import org.projectzion.game.utils.converter.User2UserTOConverter;
import org.projectzion.game.utils.converter.UserTO2UserConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.transaction.Transactional;


@RestController
public class UserController {

    @Autowired
    UserService userService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    UserTO2UserConverter userTO2UserConverter;

    @Autowired
    User2UserTOConverter user2UserTOConverter;

    @PutMapping("user/put")
    @Transactional
    public UserTO createUser(@RequestBody UserTO userTO){
        //TODO pfui
        userTO.setPassword(passwordEncoder.encode(userTO.getPassword()));
        User userBack = this.userService.createUser(userTO2UserConverter.convert(userTO));
        UserTO userTOBack =user2UserTOConverter.convert(userBack);
        return userTOBack;
    }



}
