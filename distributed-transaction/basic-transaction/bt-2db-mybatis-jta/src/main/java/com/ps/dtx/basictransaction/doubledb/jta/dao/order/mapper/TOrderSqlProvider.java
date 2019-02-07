package com.ps.dtx.basictransaction.doubledb.jta.dao.order.mapper;

import com.ps.dtx.basictransaction.doubledb.jta.dao.order.model.TOrder;
import org.apache.ibatis.jdbc.SQL;

public class TOrderSqlProvider {

    public String insertSelective(TOrder record) {
        SQL sql = new SQL();
        sql.INSERT_INTO("t_order");
        
        if (record.getId() != null) {
            sql.VALUES("id", "#{id,jdbcType=BIGINT}");
        }
        
        if (record.getCustomerId() != null) {
            sql.VALUES("customer_id", "#{customerId,jdbcType=BIGINT}");
        }
        
        if (record.getStorageId() != null) {
            sql.VALUES("storage_id", "#{storageId,jdbcType=BIGINT}");
        }
        
        if (record.getAmount() != null) {
            sql.VALUES("amount", "#{amount,jdbcType=INTEGER}");
        }
        
        if (record.getOrderDesc() != null) {
            sql.VALUES("order_desc", "#{orderDesc,jdbcType=VARCHAR}");
        }
        
        if (record.getCreateTime() != null) {
            sql.VALUES("create_time", "#{createTime,jdbcType=TIMESTAMP}");
        }
        
        return sql.toString();
    }

    public String updateByPrimaryKeySelective(TOrder record) {
        SQL sql = new SQL();
        sql.UPDATE("t_order");
        
        if (record.getCustomerId() != null) {
            sql.SET("customer_id = #{customerId,jdbcType=BIGINT}");
        }
        
        if (record.getStorageId() != null) {
            sql.SET("storage_id = #{storageId,jdbcType=BIGINT}");
        }
        
        if (record.getAmount() != null) {
            sql.SET("amount = #{amount,jdbcType=INTEGER}");
        }
        
        if (record.getOrderDesc() != null) {
            sql.SET("order_desc = #{orderDesc,jdbcType=VARCHAR}");
        }
        
        if (record.getCreateTime() != null) {
            sql.SET("create_time = #{createTime,jdbcType=TIMESTAMP}");
        }
        
        sql.WHERE("id = #{id,jdbcType=BIGINT}");
        
        return sql.toString();
    }
}