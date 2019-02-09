package com.ps.dtx.fd.storage.dao.mapper;

import static org.apache.ibatis.jdbc.SqlBuilder.BEGIN;
import static org.apache.ibatis.jdbc.SqlBuilder.INSERT_INTO;
import static org.apache.ibatis.jdbc.SqlBuilder.SET;
import static org.apache.ibatis.jdbc.SqlBuilder.SQL;
import static org.apache.ibatis.jdbc.SqlBuilder.UPDATE;
import static org.apache.ibatis.jdbc.SqlBuilder.VALUES;
import static org.apache.ibatis.jdbc.SqlBuilder.WHERE;

import com.ps.dtx.fd.storage.dao.model.TStorage;

public class TStorageSqlProvider {

    public String insertSelective(TStorage record) {
        BEGIN();
        INSERT_INTO("t_storage");
        
        if (record.getId() != null) {
            VALUES("id", "#{id,jdbcType=BIGINT}");
        }
        
        if (record.getProductName() != null) {
            VALUES("product_name", "#{productName,jdbcType=VARCHAR}");
        }
        
        if (record.getInventory() != null) {
            VALUES("inventory", "#{inventory,jdbcType=INTEGER}");
        }
        
        if (record.getCreateTime() != null) {
            VALUES("create_time", "#{createTime,jdbcType=TIMESTAMP}");
        }
        
        if (record.getUpdateTime() != null) {
            VALUES("update_time", "#{updateTime,jdbcType=TIMESTAMP}");
        }
        
        return SQL();
    }

    public String updateByPrimaryKeySelective(TStorage record) {
        BEGIN();
        UPDATE("t_storage");
        
        if (record.getProductName() != null) {
            SET("product_name = #{productName,jdbcType=VARCHAR}");
        }
        
        if (record.getInventory() != null) {
            SET("inventory = #{inventory,jdbcType=INTEGER}");
        }
        
        if (record.getCreateTime() != null) {
            SET("create_time = #{createTime,jdbcType=TIMESTAMP}");
        }
        
        if (record.getUpdateTime() != null) {
            SET("update_time = #{updateTime,jdbcType=TIMESTAMP}");
        }
        
        WHERE("id = #{id,jdbcType=BIGINT}");
        
        return SQL();
    }
}