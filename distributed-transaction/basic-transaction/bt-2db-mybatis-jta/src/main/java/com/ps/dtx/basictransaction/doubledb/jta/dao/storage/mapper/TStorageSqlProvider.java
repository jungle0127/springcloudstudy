package com.ps.dtx.basictransaction.doubledb.jta.dao.storage.mapper;

import com.ps.dtx.basictransaction.doubledb.jta.dao.storage.model.TStorage;
import org.apache.ibatis.jdbc.SQL;

public class TStorageSqlProvider {

    public String insertSelective(TStorage record) {
        SQL sql = new SQL();
        sql.INSERT_INTO("t_storage");
        
        if (record.getId() != null) {
            sql.VALUES("id", "#{id,jdbcType=BIGINT}");
        }
        
        if (record.getProductName() != null) {
            sql.VALUES("product_name", "#{productName,jdbcType=VARCHAR}");
        }
        
        if (record.getInventory() != null) {
            sql.VALUES("inventory", "#{inventory,jdbcType=INTEGER}");
        }
        
        if (record.getCreateTime() != null) {
            sql.VALUES("create_time", "#{createTime,jdbcType=TIMESTAMP}");
        }
        
        if (record.getUpdateTime() != null) {
            sql.VALUES("update_time", "#{updateTime,jdbcType=TIMESTAMP}");
        }
        
        return sql.toString();
    }

    public String updateByPrimaryKeySelective(TStorage record) {
        SQL sql = new SQL();
        sql.UPDATE("t_storage");
        
        if (record.getProductName() != null) {
            sql.SET("product_name = #{productName,jdbcType=VARCHAR}");
        }
        
        if (record.getInventory() != null) {
            sql.SET("inventory = #{inventory,jdbcType=INTEGER}");
        }
        
        if (record.getCreateTime() != null) {
            sql.SET("create_time = #{createTime,jdbcType=TIMESTAMP}");
        }
        
        if (record.getUpdateTime() != null) {
            sql.SET("update_time = #{updateTime,jdbcType=TIMESTAMP}");
        }
        
        sql.WHERE("id = #{id,jdbcType=BIGINT}");
        
        return sql.toString();
    }
}