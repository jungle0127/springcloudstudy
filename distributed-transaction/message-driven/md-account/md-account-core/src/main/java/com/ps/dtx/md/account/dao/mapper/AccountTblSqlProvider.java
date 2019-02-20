package com.ps.dtx.md.account.dao.mapper;

import com.ps.dtx.md.account.dao.model.AccountTbl;
import org.apache.ibatis.jdbc.SQL;

public class AccountTblSqlProvider {

    public String insertSelective(AccountTbl record) {
        SQL sql = new SQL();
        sql.INSERT_INTO("account_tbl");
        
        if (record.getId() != null) {
            sql.VALUES("id", "#{id,jdbcType=INTEGER}");
        }
        
        if (record.getUserId() != null) {
            sql.VALUES("user_id", "#{userId,jdbcType=VARCHAR}");
        }
        
        if (record.getMoney() != null) {
            sql.VALUES("money", "#{money,jdbcType=INTEGER}");
        }
        
        return sql.toString();
    }

    public String updateByPrimaryKeySelective(AccountTbl record) {
        SQL sql = new SQL();
        sql.UPDATE("account_tbl");
        
        if (record.getUserId() != null) {
            sql.SET("user_id = #{userId,jdbcType=VARCHAR}");
        }
        
        if (record.getMoney() != null) {
            sql.SET("money = #{money,jdbcType=INTEGER}");
        }
        
        sql.WHERE("id = #{id,jdbcType=INTEGER}");
        
        return sql.toString();
    }
}