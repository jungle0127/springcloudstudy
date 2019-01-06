package com.ps.tx.md.ticket.dao.mapper;

import com.ps.tx.md.ticket.dao.model.Ticket;
import org.apache.ibatis.jdbc.SQL;

public class TicketSqlProvider {

    public String insertSelective(Ticket record) {
        SQL sql = new SQL();
        sql.INSERT_INTO("ticket");
        
        if (record.getId() != null) {
            sql.VALUES("id", "#{id,jdbcType=BIGINT}");
        }
        
        if (record.getName() != null) {
            sql.VALUES("name", "#{name,jdbcType=VARCHAR}");
        }
        
        if (record.getOwner() != null) {
            sql.VALUES("owner", "#{owner,jdbcType=BIGINT}");
        }
        
        if (record.getLockUser() != null) {
            sql.VALUES("lock_user", "#{lockUser,jdbcType=BIGINT}");
        }
        
        if (record.getTicketNumber() != null) {
            sql.VALUES("ticket_number", "#{ticketNumber,jdbcType=BIGINT}");
        }
        
        return sql.toString();
    }

    public String updateByPrimaryKeySelective(Ticket record) {
        SQL sql = new SQL();
        sql.UPDATE("ticket");
        
        if (record.getName() != null) {
            sql.SET("name = #{name,jdbcType=VARCHAR}");
        }
        
        if (record.getOwner() != null) {
            sql.SET("owner = #{owner,jdbcType=BIGINT}");
        }
        
        if (record.getLockUser() != null) {
            sql.SET("lock_user = #{lockUser,jdbcType=BIGINT}");
        }
        
        if (record.getTicketNumber() != null) {
            sql.SET("ticket_number = #{ticketNumber,jdbcType=BIGINT}");
        }
        
        sql.WHERE("id = #{id,jdbcType=BIGINT}");
        
        return sql.toString();
    }
}