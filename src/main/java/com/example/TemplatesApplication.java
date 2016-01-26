package com.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.security.AuthenticationManagerConfiguration;
import org.springframework.boot.autoconfigure.security.BootGlobalAuthenticationConfiguration;
import org.springframework.boot.autoconfigure.security.SecurityAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfiguration;
import org.springframework.security.config.annotation.web.servlet.configuration.WebMvcSecurityConfiguration;

@Configuration
@EnableAutoConfiguration(exclude={SecurityAutoConfiguration.class,WebMvcSecurityConfiguration.class,WebSecurityConfiguration.class,AuthenticationConfiguration.class,AuthenticationManagerConfiguration.class,BootGlobalAuthenticationConfiguration.class})
@ComponentScan
public class TemplatesApplication {

    public static void main(String[] args) {
        SpringApplication.run(TemplatesApplication.class, args);
    }
}