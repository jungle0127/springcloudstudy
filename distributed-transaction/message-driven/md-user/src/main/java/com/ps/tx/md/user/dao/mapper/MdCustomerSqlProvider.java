package com.ps.tx.md.user.dao.mapper;

import com.ps.tx.md.user.dao.model.MdCustomer;
import org.apache.ibatis.jdbc.SQL;

public class MdCustomerSqlProvider {

    public String insertSelective(MdCustomer record) {
        SQL sql = new SQL();
        sql.INSERT_INTO("md_customer");
        
        if (record.getId() != null) {
            sql.VALUES("id", "#{id,jdbcType=BIGINT}");
        }
        
        if (record.getUsername() != null) {
            sql.VALUES("username", "#{username,jdbcType=VARCHAR}");
        }
        
        if (record.getPassword() != null) {
            sql.VALUES("password", "#{password,jdbcType=VARCHAR}");
        }
        
        if (record.getRole() != null) {
            sql.VALUES("role", "#{role,jdbcType=VARCHAR}");
        }
        
        if (record.getDeposit() != null) {
            sql.VALUES("deposit", "#{deposit,jdbcType=INTEGER}");
        }
        
        return sql.toString();
    }

    public String updateByPrimaryKeySelective(MdCustomer record) {
        SQL sql = new SQL();
        sql.UPDATE("md_customer");
        
        if (record.getUsername() != null) {
            sql.SET("username = #{username,jdbcType=VARCHAR}");
        }
        
        if (record.getPassword() != null) {
            sql.SET("password = #{password,jdbcType=VARCHAR}");
        }
        
        if (record.getRole() != null) {
            sql.SET("role = #{role,jdbcType=VARCHAR}");
        }
        
        if (record.getDeposit() != null) {
            sql.SET("deposit = #{deposit,jdbcType=INTEGER}");
        }
        
        sql.WHERE("id = #{id,jdbcType=BIGINT}");
        
        return sql.toString();
    }
}