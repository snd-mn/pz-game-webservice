package com.example.restservice.configs.auth;

import com.example.restservice.persitence.entities.security.User;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.Collection;

@Data
@Configuration
@ConfigurationProperties(prefix = "pz.auth")
public class AuthFromPropertiesConfiguration {
    //com.example.restservice.configs.UserConverter
    Collection<User> inital_users;
}
