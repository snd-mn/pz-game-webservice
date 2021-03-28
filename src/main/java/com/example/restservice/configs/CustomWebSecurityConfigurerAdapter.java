package com.example.restservice.configs;

import com.example.restservice.auth.MyBasicAuthenticationEntryPoint;
import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class CustomWebSecurityConfigurerAdapter extends WebSecurityConfigurerAdapter {
    AuthenticationManagerBuilder auth = null;
    @Autowired
    private MyBasicAuthenticationEntryPoint authenticationEntryPoint;

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        //TODO super duper initaluser in application.properties wie xwiki
        //TODO db stuff? vllt LoginController schreiben oder sowat
        this.auth = auth;
        auth.inMemoryAuthentication().withUser("admin").password(passwordEncoder().encode("superduperkalifragiexpialiismirzulang")).authorities("ROLE_ADMIN");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests().antMatchers("/securityNone").permitAll().anyRequest().authenticated().and().httpBasic().authenticationEntryPoint(authenticationEntryPoint);
        //@JAHA -> stuff ausm tut: https://www.baeldung.com/spring-security-basic-authentication

        //meine version (funzt nich)
        http.addFilter(new BasicAuthenticationFilter(auth.build()));
//        http.addFilterAfter(new HttpFilter() {
//        }, BasicAuthenticationFilter.class);
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
