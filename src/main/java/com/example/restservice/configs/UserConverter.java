package com.example.restservice.configs;

import com.example.restservice.persitence.entities.User;
import org.springframework.boot.context.properties.ConfigurationPropertiesBinding;
import org.springframework.context.annotation.Bean;
import org.springframework.core.convert.converter.Converter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
@ConfigurationPropertiesBinding
public class UserConverter implements Converter<String, User> {

    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    public User convert(String s) {
        User user = new User();
        String[] splits = s.split(",");
        user.setName(splits[0]);
        user.setPassword(passwordEncoder().encode(splits[1]));
        user.setEmail(splits[2]);
        user.setNick(splits[3]);
        return user;
    }
}
