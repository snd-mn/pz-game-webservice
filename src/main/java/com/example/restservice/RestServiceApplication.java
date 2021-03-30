package com.example.restservice;

//import com.example.restservice.configs.auth.AuthFromPropertiesConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;


@SpringBootApplication
//@EnableConfigurationProperties(AuthFromPropertiesConfiguration.class)
//@ImportResource("classpath:beans.xml")
public class RestServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(RestServiceApplication.class, args);
    }

}
