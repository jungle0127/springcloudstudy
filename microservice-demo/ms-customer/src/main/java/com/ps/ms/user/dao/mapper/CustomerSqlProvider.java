package com.ps.ms.user.dao.mapper;

import com.ps.ms.user.dao.model.Customer;
import org.apache.ibatis.jdbc.SQL;

public class CustomerSqlProvider {

    public String insertSelective(Customer record) {
        SQL sql = new SQL();
        sql.INSERT_INTO("customer");

        if (record.getId() != null) {
            sql.VALUES("id", "#{id,jdbcType=BIGINT}");
        }

        if (record.getUsername() != null) {
            sql.VALUES("username", "#{username,jdbcType=VARCHAR}");
        }

        if (record.getPassword() != null) {
            sql.VALUES("password", "#{password,jdbcType=VARCHAR}");
        }

        if (record.getDeposit() != null) {
            sql.VALUES("deposit", "#{deposit,jdbcType=INTEGER}");
        }

        return sql.toString();
    }

    public String updateByPrimaryKeySelective(Customer record) {
        SQL sql = new SQL();
        sql.UPDATE("customer");

        if (record.getUsername() != null) {
            sql.SET("username = #{username,jdbcType=VARCHAR}");
        }

        if (record.getPassword() != null) {
            sql.SET("password = #{password,jdbcType=VARCHAR}");
        }

        if (record.getDeposit() != null) {
            sql.SET("deposit = #{deposit,jdbcType=INTEGER}");
        }

        sql.WHERE("id = #{id,jdbcType=BIGINT}");

        return sql.toString();
    }
}