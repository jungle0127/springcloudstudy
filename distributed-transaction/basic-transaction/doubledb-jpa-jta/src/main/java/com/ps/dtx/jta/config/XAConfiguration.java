package com.ps.dtx.jta.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "spring.xa")
public class XAConfiguration {
    private Integer minPoolSize = 5;
    private Integer maxPoolSize = 10;
    private Integer maxLifeTime = 3000;
    private Integer borrowConnectionTimeout = 3000;
    private Integer loginTimeout = 3000;
    private Integer maintainceInterval = 2000;
    private Integer maxIdleTime = 3000;

    public Integer getMinPoolSize() {
        return minPoolSize;
    }

    public void setMinPoolSize(Integer minPoolSize) {
        this.minPoolSize = minPoolSize;
    }

    public Integer getMaxPoolSize() {
        return maxPoolSize;
    }

    public void setMaxPoolSize(Integer maxPoolSize) {
        this.maxPoolSize = maxPoolSize;
    }

    public Integer getMaxLifeTime() {
        return maxLifeTime;
    }

    public void setMaxLifeTime(Integer maxLifeTime) {
        this.maxLifeTime = maxLifeTime;
    }

    public Integer getBorrowConnectionTimeout() {
        return borrowConnectionTimeout;
    }

    public void setBorrowConnectionTimeout(Integer borrowConnectionTimeout) {
        this.borrowConnectionTimeout = borrowConnectionTimeout;
    }

    public Integer getLoginTimeout() {
        return loginTimeout;
    }

    public void setLoginTimeout(Integer loginTimeout) {
        this.loginTimeout = loginTimeout;
    }

    public Integer getMaintainceInterval() {
        return maintainceInterval;
    }

    public void setMaintainceInterval(Integer maintainceInterval) {
        this.maintainceInterval = maintainceInterval;
    }

    public Integer getMaxIdleTime() {
        return maxIdleTime;
    }

    public void setMaxIdleTime(Integer maxIdleTime) {
        this.maxIdleTime = maxIdleTime;
    }

    @Override
    public String toString() {
        return "XAConfiguration{" +
                "minPoolSize=" + minPoolSize +
                ", maxPoolSize=" + maxPoolSize +
                ", maxLifeTime=" + maxLifeTime +
                ", borrowConnectionTimeout=" + borrowConnectionTimeout +
                ", loginTimeout=" + loginTimeout +
                ", maintainceInterval=" + maintainceInterval +
                ", maxIdleTime=" + maxIdleTime +
                '}';
    }
}
