package com.ps.dtx.fd.storage;

import com.alibaba.dubbo.config.spring.context.annotation.EnableDubbo;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan(basePackages = "com.ps.dtx.fd.storage.dao.mapper")
@EnableDubbo(scanBasePackages = "com.ps.dtx.fd.storage.service")
public class StorageApplication {
    public static void main(String[] args) {
        SpringApplication.run(StorageApplication.class,args);
    }
}
