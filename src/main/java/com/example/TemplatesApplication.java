package com.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableAutoConfiguration
//@EnableAutoConfiguration(exclude={SecurityAutoConfiguration.class,WebMvcSecurityConfiguration.class,WebSecurityConfiguration.class,AuthenticationConfiguration.class,AuthenticationManagerConfiguration.class,BootGlobalAuthenticationConfiguration.class})
@ComponentScan
public class TemplatesApplication {

    public static void main(String[] args) {
        SpringApplication.run(TemplatesApplication.class, args);
    }
}