package com.ps.tx.md.ticket.dao.mapper;

import com.ps.tx.md.ticket.dao.model.Ticket;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.apache.ibatis.annotations.UpdateProvider;
import org.apache.ibatis.type.JdbcType;

public interface TicketMapper {
    @Delete({
        "delete from ticket",
        "where id = #{id,jdbcType=BIGINT}"
    })
    int deleteByPrimaryKey(Long id);

    @Insert({
        "insert into ticket (id, name, ",
        "owner, lock_user, ticket_number)",
        "values (#{id,jdbcType=BIGINT}, #{name,jdbcType=VARCHAR}, ",
        "#{owner,jdbcType=BIGINT}, #{lockUser,jdbcType=BIGINT}, #{ticketNumber,jdbcType=BIGINT})"
    })
    int insert(Ticket record);

    @InsertProvider(type=TicketSqlProvider.class, method="insertSelective")
    int insertSelective(Ticket record);

    @Select({
        "select",
        "id, name, owner, lock_user, ticket_number",
        "from ticket",
        "where id = #{id,jdbcType=BIGINT}"
    })
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.BIGINT, id=true),
        @Result(column="name", property="name", jdbcType=JdbcType.VARCHAR),
        @Result(column="owner", property="owner", jdbcType=JdbcType.BIGINT),
        @Result(column="lock_user", property="lockUser", jdbcType=JdbcType.BIGINT),
        @Result(column="ticket_number", property="ticketNumber", jdbcType=JdbcType.BIGINT)
    })
    Ticket selectByPrimaryKey(Long id);
    @Select({
            "select",
            "id, name, owner, lock_user, ticket_number",
            "from ticket",
            "where ticket_number = #{ticketNumber,jdbcType=BIGINT}"
    })
    @Results({
            @Result(column="id", property="id", jdbcType=JdbcType.BIGINT, id=true),
            @Result(column="name", property="name", jdbcType=JdbcType.VARCHAR),
            @Result(column="owner", property="owner", jdbcType=JdbcType.BIGINT),
            @Result(column="lock_user", property="lockUser", jdbcType=JdbcType.BIGINT),
            @Result(column="ticket_number", property="ticketNumber", jdbcType=JdbcType.BIGINT)
    })
    Ticket selectByTicketNumber(Long ticketNumber);

    @UpdateProvider(type=TicketSqlProvider.class, method="updateByPrimaryKeySelective")
    int updateByPrimaryKeySelective(Ticket record);

    @Update({
        "update ticket",
        "set name = #{name,jdbcType=VARCHAR},",
          "owner = #{owner,jdbcType=BIGINT},",
          "lock_user = #{lockUser,jdbcType=BIGINT},",
          "ticket_number = #{ticketNumber,jdbcType=BIGINT}",
        "where id = #{id,jdbcType=BIGINT}"
    })
    int updateByPrimaryKey(Ticket record);
    @Update({
            "update ticket set lock_user= #{customerId, jdbcType=BIGINT} where lock_user is NOT null and ticket_number=#{ticketNumber, jdbcType=BIGINT}"
    })
    int updateByTicketNumber(Long customerId, Long ticketNumber);
}