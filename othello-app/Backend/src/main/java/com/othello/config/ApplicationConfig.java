package com.othello.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@PropertySource("classpath:othello.properties")
@ComponentScan({ "com.othello" })


public class ApplicationConfig {

}