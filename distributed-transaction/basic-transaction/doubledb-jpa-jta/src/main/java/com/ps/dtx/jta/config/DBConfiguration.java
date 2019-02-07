package com.ps.dtx.jta.config;

import com.mysql.jdbc.jdbc2.optional.MysqlXADataSource;
import com.zaxxer.hikari.HikariDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jta.atomikos.AtomikosDataSourceBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;
import java.sql.SQLException;

@Configuration
public class DBConfiguration {
    @Autowired
    private XAConfiguration xaConfiguration;

    @Primary
    @Bean
    @ConfigurationProperties(prefix = "spring.order-datasource")
    public DataSourceProperties orderDataSourceProperties(){
        return new DataSourceProperties();
    }

    @Primary
    @Bean
    public DataSource orderDataSource() throws SQLException {
        MysqlXADataSource xaDataSource = new MysqlXADataSource();
        DataSourceProperties dataSourceProperties = orderDataSourceProperties();
        xaDataSource.setUrl(dataSourceProperties.getUrl());
        xaDataSource.setPinGlobalTxToPhysicalConnection(true);
        xaDataSource.setPassword(dataSourceProperties.getPassword());
        xaDataSource.setUser(dataSourceProperties.getUsername());

        AtomikosDataSourceBean atomikosDataSourceBean = new AtomikosDataSourceBean();
        atomikosDataSourceBean.setXaDataSource(xaDataSource);
        atomikosDataSourceBean.setUniqueResourceName("orderDataSource");
        atomikosDataSourceBean.setMinPoolSize(xaConfiguration.getMinPoolSize());
        atomikosDataSourceBean.setMaxPoolSize(xaConfiguration.getMaxPoolSize());
        atomikosDataSourceBean.setMaxLifetime(xaConfiguration.getMaxLifeTime());
        atomikosDataSourceBean.setBorrowConnectionTimeout(xaConfiguration.getBorrowConnectionTimeout());
        atomikosDataSourceBean.setLoginTimeout(xaConfiguration.getLoginTimeout());
        atomikosDataSourceBean.setMaintenanceInterval(xaConfiguration.getMaintainceInterval());
        atomikosDataSourceBean.setMaxIdleTime(xaConfiguration.getMaxIdleTime());
        return atomikosDataSourceBean;
//        return orderDataSourceProperties().initializeDataSourceBuilder().type(HikariDataSource.class).build();
    }
    @Bean
    public JdbcTemplate orderJdbcTemplate(@Qualifier("orderDataSource") DataSource orderDataSource){
        return new JdbcTemplate(orderDataSource);
    }
    @Bean
    @ConfigurationProperties(prefix = "spring.storage-datasource")
    public DataSourceProperties storeageDataSourceProperties(){
        return new DataSourceProperties();
    }

    @Bean
    public DataSource storageDataSource(){
        return storeageDataSourceProperties().initializeDataSourceBuilder().type(HikariDataSource.class).build();
    }
    @Bean
    public JdbcTemplate storeageJdbcTemplate(@Qualifier("storageDataSource") DataSource storeageDataSource){
        return new JdbcTemplate(storeageDataSource);
    }
}
