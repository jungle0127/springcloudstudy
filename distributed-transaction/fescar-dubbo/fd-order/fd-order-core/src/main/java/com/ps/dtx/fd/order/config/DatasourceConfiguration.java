package com.ps.dtx.fd.order.config;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.fescar.rm.datasource.DataSourceProxy;
import com.alibaba.fescar.spring.annotation.GlobalTransactionScanner;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import javax.sql.DataSource;

@Configuration
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
        return sqlSessionFactoryBean.getObject();
    }
    @Bean
    public DataSourceTransactionManager dataSourceTransactionManager(){
        return new DataSourceTransactionManager(dataSourceProxy());
    }
    @Bean
    public GlobalTransactionScanner globalTransactionScanner(){
        return new GlobalTransactionScanner("fescar-order","fescar-dubbo-nacos-group");
    }
}
