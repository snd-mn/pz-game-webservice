package org.projectzion.game;

//import com.example.restservice.configs.auth.AuthFromPropertiesConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
//@EnableConfigurationProperties(AuthFromPropertiesConfiguration.class)
public class RestServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(RestServiceApplication.class, args);
    }

}
