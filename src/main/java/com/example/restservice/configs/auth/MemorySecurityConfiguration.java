package com.example.restservice.configs.auth;

import com.example.restservice.utils.UserRole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class MemorySecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Autowired
    AuthConfiguration authConfiguration;

    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception {
        httpSecurity.csrf().disable()
                .authorizeRequests().anyRequest().authenticated()
                .and().httpBasic();

    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder authentication)
            throws Exception
    {
        authentication.inMemoryAuthentication()
                .withUser(authConfiguration.getSuper_user().getName())
                .password(authConfiguration.getSuper_user().getPassword())
                .authorities(UserRole.SUPER_USER.toString())
                .and().withUser(authConfiguration.getSuper_user().getEmail())
                .password(authConfiguration.getSuper_user().getPassword())
                .authorities(UserRole.SUPER_USER.toString());
    }

}
