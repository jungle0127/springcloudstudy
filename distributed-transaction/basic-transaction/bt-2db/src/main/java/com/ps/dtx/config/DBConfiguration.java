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

@Configuration
public class DBConfiguration {
    @Primary
    @Bean
    @ConfigurationProperties(prefix = "spring.storeage-datasource")
    public DataSourceProperties storeageDataSourceProperties(){
        return new DataSourceProperties();
    }
    @Bean
    @Primary
    public DataSource storeageDataSource(){
        return storeageDataSourceProperties().initializeDataSourceBuilder().type(HikariDataSource.class).build();
    }
    @Bean
    public JdbcTemplate storeageJdbcTemplate(@Qualifier("storeageDataSource") DataSource storeageDataSource){
        return new JdbcTemplate(storeageDataSource);
    }

    @Bean
    @ConfigurationProperties(prefix = "spring.order-datasource")
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
