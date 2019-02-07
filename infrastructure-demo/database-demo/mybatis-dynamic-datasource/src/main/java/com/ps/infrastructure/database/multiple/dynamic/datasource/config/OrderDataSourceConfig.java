package com.ps.infrastructure.database.multiple.dynamic.datasource.config;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import javax.sql.DataSource;

@Configuration
@MapperScan(basePackages = "com.ps.infrastructure.database.multiple.dynamic.datasource.dao.order.mapper", sqlSessionTemplateRef = "orderSqlSessionTemplate")
public class OrderDataSourceConfig {
    @Bean("orderDataSource")
    @ConfigurationProperties(prefix = "spring.order-datasource")
    public DataSource orderDataSource(){
        return DataSourceBuilder.create().build();
    }
    @Bean("orderSqlSessionFactory")
    public SqlSessionFactory orderSqlSessionFactory(@Qualifier("orderDataSource") DataSource dataSource) throws Exception {
        SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
        sqlSessionFactoryBean.setDataSource(dataSource);
        return sqlSessionFactoryBean.getObject();
    }
    @Bean("orderDataSourceTransactionManager")
    public DataSourceTransactionManager orderDataSourceTransactionManager(@Qualifier("orderDataSource") DataSource dataSource){
        return new DataSourceTransactionManager(dataSource);
    }
    @Bean("orderSqlSessionTemplate")
    public SqlSessionTemplate orderSqlSessionTemplate(@Qualifier("orderSqlSessionFactory") SqlSessionFactory sqlSessionFactory){
        return new SqlSessionTemplate(sqlSessionFactory);
    }
}
