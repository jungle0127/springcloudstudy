package com.ps.tx.jta.dao.mapper;

import com.ps.tx.jta.dao.model.TAccount;
import org.apache.ibatis.jdbc.SQL;

public class TAccountSqlProvider {

    public String insertSelective(TAccount record) {
        SQL sql = new SQL();
        sql.INSERT_INTO("t_account");
        
        if (record.getId() != null) {
            sql.VALUES("id", "#{id,jdbcType=BIGINT}");
        }
        
        if (record.getCustomerId() != null) {
            sql.VALUES("customer_id", "#{customerId,jdbcType=BIGINT}");
        }
        
        if (record.getDeposit() != null) {
            sql.VALUES("deposit", "#{deposit,jdbcType=INTEGER}");
        }
        
        if (record.getCreateTime() != null) {
            sql.VALUES("create_time", "#{createTime,jdbcType=TIMESTAMP}");
        }
        
        if (record.getUpdateTime() != null) {
            sql.VALUES("update_time", "#{updateTime,jdbcType=TIMESTAMP}");
        }
        
        return sql.toString();
    }

    public String updateByPrimaryKeySelective(TAccount record) {
        SQL sql = new SQL();
        sql.UPDATE("t_account");
        
        if (record.getCustomerId() != null) {
            sql.SET("customer_id = #{customerId,jdbcType=BIGINT}");
        }
        
        if (record.getDeposit() != null) {
            sql.SET("deposit = #{deposit,jdbcType=INTEGER}");
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