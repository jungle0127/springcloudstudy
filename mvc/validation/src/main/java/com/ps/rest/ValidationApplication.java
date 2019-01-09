package com.ps.rest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableSwagger2
public class ValidationApplication {
    public static void main(String[] args) {
        SpringApplication.run(ValidationApplication.class,args);
    }
}
