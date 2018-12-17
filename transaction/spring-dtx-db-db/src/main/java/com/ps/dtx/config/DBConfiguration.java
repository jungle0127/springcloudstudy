package com.ps.dtx.config;

import com.zaxxer.hikari.HikariDataSource;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;
import javax.xml.crypto.Data;

@Configuration
public class DBConfiguration {
    @Bean
    @Primary
    @ConfigurationProperties(prefix = "spring.userDatasource")
    public DataSourceProperties userDataSourceProperties(){
        return new DataSourceProperties();
    }
    @Bean
    @Primary
    public DataSource userDataSource(){
        return userDataSourceProperties().initializeDataSourceBuilder().type(HikariDataSource.class).build();
    }
    @Bean
    public JdbcTemplate userJdbcTemplate(@Qualifier("userDataSource") DataSource userDataSource){
        return new JdbcTemplate(userDataSource);
    }
    @Bean
    @ConfigurationProperties(prefix = "spring.orderDatasource")
    public DataSourceProperties orderDataSourceProperties(){
        return new DataSourceProperties();
    }
    @Bean
    public DataSource orderDataSource(){
        return orderDataSourceProperties().initializeDataSourceBuilder().type(HikariDataSource.class).build();
    }
    @Bean
    public JdbcTemplate orderJdbcTemplate(@Qualifier("orderDataSource") DataSource orderDataSource){
        return new JdbcTemplate(orderDataSource);
    }
}
