package com.ps.tx.jta.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jta.atomikos.AtomikosDataSourceBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

import javax.sql.DataSource;
import java.util.Properties;

@Configuration
public class DatasourceConfig {
    @Autowired
    private Environment env;
    @Bean
    public DataSource dataSource(){
        AtomikosDataSourceBean dataSourceBean = new AtomikosDataSourceBean();
        dataSourceBean.setXaDataSourceClassName("com.alibaba.druid.pool.xa.DruidXADataSource");
        dataSourceBean.setBeanName("dataSource");
        dataSourceBean.setPoolSize(5);
        dataSourceBean.setXaProperties(this.build("spring.datasource."));
        return dataSourceBean;
    }

    private Properties build(String prefix) {
        Properties prop = new Properties();
        prop.put("url", env.getProperty(prefix + "url"));
        prop.put("username", env.getProperty(prefix + "username"));
        prop.put("password", env.getProperty(prefix + "password"));
        prop.put("driverClassName", env.getProperty(prefix + "driver-class-name"));

        return prop;
    }
}
