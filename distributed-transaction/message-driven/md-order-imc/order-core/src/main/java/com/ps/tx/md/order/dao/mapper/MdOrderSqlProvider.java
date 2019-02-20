package com.ps.tx.md.order.dao.mapper;

import com.ps.tx.md.order.dao.model.MdOrder;
import org.apache.ibatis.jdbc.SQL;

public class MdOrderSqlProvider {

    public String insertSelective(MdOrder record) {
        SQL sql = new SQL();
        sql.INSERT_INTO("md_order");

        if (record.getId() != null) {
            sql.VALUES("id", "#{id,jdbcType=BIGINT}");
        }

        if (record.getUuid() != null) {
            sql.VALUES("uuid", "#{uuid,jdbcType=CHAR}");
        }

        if (record.getCustomerId() != null) {
            sql.VALUES("customer_id", "#{customerId,jdbcType=BIGINT}");
        }

        if (record.getTitle() != null) {
            sql.VALUES("title", "#{title,jdbcType=VARCHAR}");
        }

        if (record.getTicketNumber() != null) {
            sql.VALUES("ticket_number", "#{ticketNumber,jdbcType=BIGINT}");
        }

        if (record.getAmount() != null) {
            sql.VALUES("amount", "#{amount,jdbcType=INTEGER}");
        }

        if (record.getStatus() != null) {
            sql.VALUES("status", "#{status,jdbcType=CHAR}");
        }

        if (record.getReason() != null) {
            sql.VALUES("reason", "#{reason,jdbcType=VARCHAR}");
        }

        return sql.toString();
    }

    public String updateByPrimaryKeySelective(MdOrder record) {
        SQL sql = new SQL();
        sql.UPDATE("md_order");

        if (record.getUuid() != null) {
            sql.SET("uuid = #{uuid,jdbcType=CHAR}");
        }

        if (record.getCustomerId() != null) {
            sql.SET("customer_id = #{customerId,jdbcType=BIGINT}");
        }

        if (record.getTitle() != null) {
            sql.SET("title = #{title,jdbcType=VARCHAR}");
        }

        if (record.getTicketNumber() != null) {
            sql.SET("ticket_number = #{ticketNumber,jdbcType=BIGINT}");
        }

        if (record.getAmount() != null) {
            sql.SET("amount = #{amount,jdbcType=INTEGER}");
        }

        if (record.getStatus() != null) {
            sql.SET("status = #{status,jdbcType=CHAR}");
        }

        if (record.getReason() != null) {
            sql.SET("reason = #{reason,jdbcType=VARCHAR}");
        }

        sql.WHERE("id = #{id,jdbcType=BIGINT}");

        return sql.toString();
    }
}