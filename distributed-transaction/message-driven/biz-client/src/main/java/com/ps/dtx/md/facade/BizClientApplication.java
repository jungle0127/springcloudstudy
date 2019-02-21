package com.ps.dtx.md.facade;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableSwagger2
public class BizClientApplication {
    public static void main(String[] args) {
        SpringApplication.run(BizClientApplication.class,args);
    }
}
