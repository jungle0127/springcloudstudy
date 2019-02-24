package com.ps.dtx.fd.account.config;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.fescar.rm.datasource.DataSourceProxy;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.transaction.jdbc.JdbcTransactionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import javax.sql.DataSource;

@Configuration
@MapperScan(basePackages = "com.ps.dtx.fd.account.dao.mapper")
public class DatasourceConfiguration {
    @Autowired
    private DataSourceProperties dataSourceProperties;
    @Bean
    public DruidDataSource druidDataSource(){
        DruidDataSource druidDataSource = new DruidDataSource();
        druidDataSource.setUrl(this.dataSourceProperties.getUrl());
        druidDataSource.setUsername(this.dataSourceProperties.getUsername());
        druidDataSource.setPassword(this.dataSourceProperties.getPassword());
        druidDataSource.setDriverClassName(this.dataSourceProperties.getDriverClassName());
        druidDataSource.setUseGlobalDataSourceStat(true);
        return druidDataSource;
    }
    @Bean
    public DataSource dataSourceProxy(){
        return new DataSourceProxy(druidDataSource());
    }
    @Bean
    public SqlSessionFactory sqlSessionFactory() throws Exception {
        SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
        sqlSessionFactoryBean.setDataSource(dataSourceProxy());
        sqlSessionFactoryBean.setTransactionFactory(new JdbcTransactionFactory());
        return sqlSessionFactoryBean.getObject();
    }
    @Bean
    public DataSourceTransactionManager dataSourceTransactionManager(){
        return new DataSourceTransactionManager(dataSourceProxy());
    }


}
