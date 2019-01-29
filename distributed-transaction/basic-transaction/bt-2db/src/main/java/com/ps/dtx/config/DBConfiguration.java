package com.ps.dtx.config;

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
    @Bean
    @Primary
    @ConfigurationProperties(prefix = "spring.user-datasource")
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
    public PlatformTransactionManager transactionManager(){
        DataSourceTransactionManager userTM = new DataSourceTransactionManager(userDataSource());
        // userDataSource(), AOP的方式从容器中获取，非简单的方法调用
        // userTM.setDataSource(userDataSource()); 这个不是从容器中获取
        DataSourceTransactionManager orderTM = new DataSourceTransactionManager(orderDataSource());
        ChainedTransactionManager chainedTransactionManager = new ChainedTransactionManager(userTM,orderTM);
        return chainedTransactionManager;
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
