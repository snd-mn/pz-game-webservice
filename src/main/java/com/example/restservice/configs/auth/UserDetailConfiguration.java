package com.example.restservice.configs.auth;

import com.example.restservice.services.CustomUserDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class UserDetailConfiguration {

//    @Autowired
//    CustomUserDetailService customUserDetailService;
//
//    @Bean(name ="user-service-ref")
//    public CustomUserDetailService customUserDetailService (){
//        return this.customUserDetailService;
//    }

    BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();// = new BCryptPasswordEncoder();

    @Bean(name = "security:password-encoder")
    PasswordEncoder passwordEncoder(){
        return this.bCryptPasswordEncoder;
    }




//    <security:authentication-manager>
//          <security:authentication-provider
//              user-service-ref="myUserDetailsService" >
//              <security:password-encoder ref="passwordEncoder">
//              </security:password-encoder>
//          </security:authentication-provider>
//    </security:authentication-manager>



}
