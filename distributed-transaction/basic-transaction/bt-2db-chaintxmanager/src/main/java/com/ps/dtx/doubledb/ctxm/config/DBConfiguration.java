package com.ps.dtx.doubledb.ctxm.config;

import com.zaxxer.hikari.HikariDataSource;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.transaction.ChainedTransactionManager;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;

@Configuration
public class DBConfiguration {
    @Primary
    @Bean
    @ConfigurationProperties(prefix = "spring.order-datasource")
    public DataSourceProperties orderDataSourceProperties(){
        return new DataSourceProperties();
    }
    @Primary
    @Bean
    public DataSource orderDataSource(){
        return orderDataSourceProperties().initializeDataSourceBuilder().type(HikariDataSource.class).build();
    }
    @Bean
    public JdbcTemplate orderJdbcTemplate(@Qualifier("orderDataSource") DataSource orderDataSource){
        return new JdbcTemplate(orderDataSource);
    }
    @Bean
    @ConfigurationProperties(prefix = "spring.storage-datasource")
    public DataSourceProperties storageDataSourceProperties(){
        return new DataSourceProperties();
    }
    @Bean
    public DataSource storageDataSource(){
        return storageDataSourceProperties().initializeDataSourceBuilder().type(HikariDataSource.class).build();
    }
    @Bean
    public JdbcTemplate storageJdbcTemplate(@Qualifier("storageDataSource") DataSource storageDataSource){
        return new JdbcTemplate(storageDataSource);
    }

    @Bean
    public PlatformTransactionManager platformTransactionManager(){
        DataSourceTransactionManager storageDataSoruceTransactionManager = new DataSourceTransactionManager(storageDataSource());
        DataSourceTransactionManager orderDataSourceTransactionManager = new DataSourceTransactionManager(orderDataSource());
        ChainedTransactionManager chainedTransactionManager = new ChainedTransactionManager(storageDataSoruceTransactionManager,orderDataSourceTransactionManager);
        return chainedTransactionManager;
    }
}
