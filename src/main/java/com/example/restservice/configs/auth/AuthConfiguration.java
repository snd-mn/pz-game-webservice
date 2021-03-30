package com.example.restservice.configs.auth;

import com.example.restservice.persitence.entities.User;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Data
@Configuration
@ConfigurationProperties(prefix = "pz.auth")
public class AuthConfiguration {
    //com.example.restservice.configs.UserConverter
    User super_user;
}
