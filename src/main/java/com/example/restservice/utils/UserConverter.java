package com.example.restservice.utils;

import com.example.restservice.persitence.entities.security.Role;
import com.example.restservice.persitence.entities.security.User;
import org.springframework.boot.context.properties.ConfigurationPropertiesBinding;
import org.springframework.core.convert.converter.Converter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

@Component
@ConfigurationPropertiesBinding
public class UserConverter implements Converter<String, User> {

    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    public User convert(String s) {
        User user = new User();
        String[] splits = s.split(";");
        user.setEmail(splits[0]);
        user.setPassword(passwordEncoder().encode(splits[1]));
        Role role = new Role(splits[2]);

        ArrayList<Role> roles = new ArrayList<>();
        roles.add(role);
        user.setRoles(roles);

        return user;
    }
}
