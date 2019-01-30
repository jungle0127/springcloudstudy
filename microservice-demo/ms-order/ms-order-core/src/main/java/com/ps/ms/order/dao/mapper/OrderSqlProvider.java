package com.ps.ms.order.dao.mapper;

import com.ps.ms.order.dao.model.Order;
import org.apache.ibatis.jdbc.SQL;

public class OrderSqlProvider {

    public String insertSelective(Order record) {
        SQL sql = new SQL();
        sql.INSERT_INTO("order");

        if (record.getId() != null) {
            sql.VALUES("id", "#{id,jdbcType=BIGINT}");
        }

        if (record.getTitle() != null) {
            sql.VALUES("title", "#{title,jdbcType=VARCHAR}");
        }

        if (record.getDetail() != null) {
            sql.VALUES("detail", "#{detail,jdbcType=VARCHAR}");
        }

        if (record.getAmount() != null) {
            sql.VALUES("amount", "#{amount,jdbcType=INTEGER}");
        }

        return sql.toString();
    }

    public String updateByPrimaryKeySelective(Order record) {
        SQL sql = new SQL();
        sql.UPDATE("order");

        if (record.getTitle() != null) {
            sql.SET("title = #{title,jdbcType=VARCHAR}");
        }

        if (record.getDetail() != null) {
            sql.SET("detail = #{detail,jdbcType=VARCHAR}");
        }

        if (record.getAmount() != null) {
            sql.SET("amount = #{amount,jdbcType=INTEGER}");
        }

        sql.WHERE("id = #{id,jdbcType=BIGINT}");

        return sql.toString();
    }
}