package com.ps.dtx.jta.config;

import com.mysql.cj.jdbc.MysqlXADataSource;
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
import javax.sql.XADataSource;
import java.sql.SQLException;

@Configuration
public class DBConfiguration {
    @Autowired
    private XAConfiguration xaConfiguration;

    @Primary
    @Bean("orderDataSourceProperties")
    @ConfigurationProperties(prefix = "spring.order-datasource")
    public DataSourceProperties orderDataSourceProperties(){
        return new DataSourceProperties();
    }

    @Bean
    public XADataSource orderXADataSource() throws SQLException {
        DataSourceProperties dataSourceProperties = orderDataSourceProperties();
        MysqlXADataSource xaDataSource = new MysqlXADataSource();
        xaDataSource.setUrl(dataSourceProperties.getUrl());
        xaDataSource.setPinGlobalTxToPhysicalConnection(true);
        xaDataSource.setPassword(dataSourceProperties.getPassword());
        xaDataSource.setUser(dataSourceProperties.getUsername());
        return xaDataSource;
    }

    @Bean("orderDataSource")
    public DataSource orderDataSource() throws SQLException {
        XADataSource xaDataSource = orderXADataSource();
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

    @Primary
    @Bean
    public JdbcTemplate orderJdbcTemplate(@Qualifier("orderDataSource") DataSource orderDataSource){
        return new JdbcTemplate(orderDataSource);
    }
    @Bean("storageDataSourceProperties")
    @ConfigurationProperties(prefix = "spring.storage-datasource")
    public DataSourceProperties storageDataSourceProperties(){
        return new DataSourceProperties();
    }

    @Bean
    public XADataSource storageXADataSource() throws SQLException {
        DataSourceProperties dataSourceProperties = storageDataSourceProperties();
        MysqlXADataSource xaDataSource = new MysqlXADataSource();
        xaDataSource.setUrl(dataSourceProperties.getUrl());
        xaDataSource.setPinGlobalTxToPhysicalConnection(true);
        xaDataSource.setPassword(dataSourceProperties.getPassword());
        xaDataSource.setUser(dataSourceProperties.getUsername());
        return xaDataSource;
    }

    @Bean("storageDataSource")
    public DataSource storageDataSource() throws SQLException {
        XADataSource xaDataSource = storageXADataSource();
        AtomikosDataSourceBean atomikosDataSourceBean = new AtomikosDataSourceBean();
        atomikosDataSourceBean.setXaDataSource(xaDataSource);
        atomikosDataSourceBean.setUniqueResourceName("storageDataSource");
        atomikosDataSourceBean.setMinPoolSize(xaConfiguration.getMinPoolSize());
        atomikosDataSourceBean.setMaxPoolSize(xaConfiguration.getMaxPoolSize());
        atomikosDataSourceBean.setMaxLifetime(xaConfiguration.getMaxLifeTime());
        atomikosDataSourceBean.setBorrowConnectionTimeout(xaConfiguration.getBorrowConnectionTimeout());
        atomikosDataSourceBean.setLoginTimeout(xaConfiguration.getLoginTimeout());
        atomikosDataSourceBean.setMaintenanceInterval(xaConfiguration.getMaintainceInterval());
        atomikosDataSourceBean.setMaxIdleTime(xaConfiguration.getMaxIdleTime());
        return atomikosDataSourceBean;
//        return storageDataSourceProperties().initializeDataSourceBuilder().type(HikariDataSource.class).build();
    }

    @Bean
    public JdbcTemplate storageJdbcTemplate(@Qualifier("storageDataSource") DataSource storageDataSource){
        return new JdbcTemplate(storageDataSource);
    }
}
