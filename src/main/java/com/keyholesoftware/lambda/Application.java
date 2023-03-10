package com.keyholesoftware.lambda;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
//import org.springframework.boot.web.support.SpringBootServletInitializer;
//@EnableAutoConfiguration(exclude={DataSourceAutoConfiguration.class})

@SpringBootApplication
//@ComponentScan(basePackages ="com.keyholesoftware.lambda.*")
public class Application{

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}