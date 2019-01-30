package com.ps.tx.md.ticket.dao.mapper;

import com.ps.tx.md.ticket.dao.model.MdTicket;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.type.JdbcType;

public interface MdTicketMapper {
    @Delete({
            "delete from md_ticket",
            "where id = #{id,jdbcType=BIGINT}"
    })
    int deleteByPrimaryKey(Long id);

    @Insert({
            "insert into md_ticket (id, name, ",
            "owner, lock_user, ticket_number)",
            "values (#{id,jdbcType=BIGINT}, #{name,jdbcType=VARCHAR}, ",
            "#{owner,jdbcType=BIGINT}, #{lockUser,jdbcType=BIGINT}, #{ticketNumber,jdbcType=BIGINT})"
    })
    int insert(MdTicket record);

    @InsertProvider(type = MdTicketSqlProvider.class, method = "insertSelective")
    int insertSelective(MdTicket record);

    @Select({
            "select",
            "id, name, owner, lock_user, ticket_number",
            "from md_ticket",
            "where id = #{id,jdbcType=BIGINT}"
    })
    @Results({
            @Result(column = "id", property = "id", jdbcType = JdbcType.BIGINT, id = true),
            @Result(column = "name", property = "name", jdbcType = JdbcType.VARCHAR),
            @Result(column = "owner", property = "owner", jdbcType = JdbcType.BIGINT),
            @Result(column = "lock_user", property = "lockUser", jdbcType = JdbcType.BIGINT),
            @Result(column = "ticket_number", property = "ticketNumber", jdbcType = JdbcType.BIGINT)
    })
    MdTicket selectByPrimaryKey(Long id);

    @UpdateProvider(type = MdTicketSqlProvider.class, method = "updateByPrimaryKeySelective")
    int updateByPrimaryKeySelective(MdTicket record);

    @Update({
            "update md_ticket",
            "set name = #{name,jdbcType=VARCHAR},",
            "owner = #{owner,jdbcType=BIGINT},",
            "lock_user = #{lockUser,jdbcType=BIGINT},",
            "ticket_number = #{ticketNumber,jdbcType=BIGINT}",
            "where id = #{id,jdbcType=BIGINT}"
    })
    int updateByPrimaryKey(MdTicket record);

    @Update({
            "update md_ticket set lock_user= #{customerId, jdbcType=BIGINT} where lock_user is null and ticket_number=#{ticketNumber, jdbcType=BIGINT}"
    })
    int updateByTicketNumber4LockTicket(@Param("customerId") Long customerId, @Param("ticketNumber") Long ticketNumber);
}