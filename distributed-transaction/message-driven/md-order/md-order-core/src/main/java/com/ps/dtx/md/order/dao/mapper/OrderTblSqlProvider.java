package com.ps.dtx.md.order.dao.mapper;

import com.ps.dtx.md.order.dao.model.OrderTbl;
import org.apache.ibatis.jdbc.SQL;

public class OrderTblSqlProvider {

    public String insertSelective(OrderTbl record) {
        SQL sql = new SQL();
        sql.INSERT_INTO("order_tbl");
        
        if (record.getId() != null) {
            sql.VALUES("id", "#{id,jdbcType=INTEGER}");
        }
        
        if (record.getUserId() != null) {
            sql.VALUES("user_id", "#{userId,jdbcType=VARCHAR}");
        }
        
        if (record.getCommodityCode() != null) {
            sql.VALUES("commodity_code", "#{commodityCode,jdbcType=VARCHAR}");
        }
        
        if (record.getCount() != null) {
            sql.VALUES("count", "#{count,jdbcType=INTEGER}");
        }
        
        if (record.getMoney() != null) {
            sql.VALUES("money", "#{money,jdbcType=INTEGER}");
        }
        
        return sql.toString();
    }

    public String updateByPrimaryKeySelective(OrderTbl record) {
        SQL sql = new SQL();
        sql.UPDATE("order_tbl");
        
        if (record.getUserId() != null) {
            sql.SET("user_id = #{userId,jdbcType=VARCHAR}");
        }
        
        if (record.getCommodityCode() != null) {
            sql.SET("commodity_code = #{commodityCode,jdbcType=VARCHAR}");
        }
        
        if (record.getCount() != null) {
            sql.SET("count = #{count,jdbcType=INTEGER}");
        }
        
        if (record.getMoney() != null) {
            sql.SET("money = #{money,jdbcType=INTEGER}");
        }
        
        sql.WHERE("id = #{id,jdbcType=INTEGER}");
        
        return sql.toString();
    }
}