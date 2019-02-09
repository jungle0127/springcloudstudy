package com.ps.dtx.fd.storage;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan(basePackages = "com.ps.dtx.fd.storage.dao.mapper")
public class StorageApplication {
    public static void main(String[] args) {
        SpringApplication.run(StorageApplication.class,args);
    }
}
