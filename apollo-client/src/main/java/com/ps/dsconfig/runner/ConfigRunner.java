package com.ps.dsconfig.runner;

import com.ps.dsconfig.config.ConfigurationHolder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class ConfigRunner implements CommandLineRunner {
    @Autowired
    private ConfigurationHolder configurationHolder;
    @Override
    public void run(String... args) throws Exception {
        System.out.println(this.configurationHolder.getDbHost());
        System.out.println(this.configurationHolder.getDbUserName());
    }
}
