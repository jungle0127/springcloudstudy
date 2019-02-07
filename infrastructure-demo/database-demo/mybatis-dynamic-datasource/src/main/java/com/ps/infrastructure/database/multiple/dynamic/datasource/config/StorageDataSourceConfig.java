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
@MapperScan(basePackages = "com.ps.infrastructure.database.multiple.dynamic.datasource.dao.storage.mapper", sqlSessionTemplateRef = "storageSqlSessionTemplate")
public class StorageDataSourceConfig {
    @Bean("storageDataSource")
    @ConfigurationProperties(prefix = "spring.storage-datasource")
    public DataSource storageDataSource(){
        return DataSourceBuilder.create().build();
    }
    @Bean("storageSqlSessionFactory")
    public SqlSessionFactory storageSqlSessionFactory(@Qualifier("storageDataSource") DataSource dataSource) throws Exception {
        SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
        sqlSessionFactoryBean.setDataSource(dataSource);
//        sqlSessionFactoryBean.setMapperLocations(new PathMatchingResourcePatternResolver().getResources(""));
        return sqlSessionFactoryBean.getObject();
    }
    @Bean("storageDataSourceTransactionManager")
    public DataSourceTransactionManager storageDataSourceTransactionManager(@Qualifier("storageDataSource") DataSource dataSource){
        return new DataSourceTransactionManager(dataSource);
    }
    @Bean("storageSqlSessionTemplate")
    public SqlSessionTemplate storageSqlSessionTemplate(@Qualifier("storageSqlSessionFactory") SqlSessionFactory sqlSessionFactory){
        return new SqlSessionTemplate(sqlSessionFactory);
    }
}
