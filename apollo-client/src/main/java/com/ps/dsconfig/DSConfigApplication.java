package com.ps.dsconfig;

import com.ctrip.framework.apollo.spring.annotation.EnableApolloConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableApolloConfig(value = {"csmp","csmp_ums_app"})
public class DSConfigApplication {
    public static void main(String[] args) {
        SpringApplication.run(DSConfigApplication.class,args);
    }
}
