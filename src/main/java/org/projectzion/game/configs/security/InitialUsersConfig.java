package org.projectzion.game.configs.security;

import lombok.Data;
import org.projectzion.game.tos.UserTO;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.Collection;

@Data
@Configuration
@ConfigurationProperties(prefix = "pz.auth")
public class InitialUsersConfig {
    //com.example.restservice.configs.UserConverter
    Collection<UserTO> inital_users;
}
