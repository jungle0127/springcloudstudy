package com.ps.tx.jta;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.ps.tx.jta.dao.mapper")
public class JTABootApplication {
    public static void main(String[] args) {
        SpringApplication.run(JTABootApplication.class, args);
    }
}
