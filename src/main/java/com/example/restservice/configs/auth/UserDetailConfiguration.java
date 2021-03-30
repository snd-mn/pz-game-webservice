package com.example.restservice.configs.auth;

import com.example.restservice.services.CustomUserDetailService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetailsService;

@Configuration
public class UserDetailConfiguration {

    @Bean
    public CustomUserDetailService customUserDetailService (){
        //TODO could fail cuz of autowired props?!?
        return new CustomUserDetailService();
    }

//    <security:authentication-manager>
//          <security:authentication-provider
//              user-service-ref="myUserDetailsService" >
//              <security:password-encoder ref="passwordEncoder">
//              </security:password-encoder>
//          </security:authentication-provider>
//    </security:authentication-manager>



}
