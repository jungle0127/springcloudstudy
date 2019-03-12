package com.ps.dsconfig.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class ConfigurationHolder {
    @Value("${redis.cluster.nodes}")
    private String dbHost;
    @Value("${csmp_ums_db_url}")
    private String dbUserName;

    public String getDbHost() {
        return dbHost;
    }

    public void setDbHost(String dbHost) {
        this.dbHost = dbHost;
    }

    public String getDbUserName() {
        return dbUserName;
    }

    public void setDbUserName(String dbUserName) {
        this.dbUserName = dbUserName;
    }
}
