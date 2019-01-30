package com.ps.tx.md.order.dao.mapper;

import com.ps.tx.md.order.dao.model.MdOrder;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.type.JdbcType;

import java.util.List;

public interface MdOrderMapper {
    @Delete({
            "delete from md_order",
            "where id = #{id,jdbcType=BIGINT}"
    })
    int deleteByPrimaryKey(Long id);

    @Insert({
            "insert into md_order (id, uuid, customer_id, ",
            "title, ticket_number, ",
            "amount, status, reason)",
            "values (#{id,jdbcType=BIGINT}, #{uuid,jdbcType=CHAR}, #{customerId,jdbcType=BIGINT}, ",
            "#{title,jdbcType=VARCHAR}, #{ticketNumber,jdbcType=BIGINT}, ",
            "#{amount,jdbcType=INTEGER}, #{status,jdbcType=CHAR}, #{reason,jdbcType=VARCHAR})"
    })
    int insert(MdOrder record);

    @InsertProvider(type = MdOrderSqlProvider.class, method = "insertSelective")
    int insertSelective(MdOrder record);

    @Select({
            "select",
            "id, uuid, customer_id, title, ticket_number, amount, status, reason",
            "from md_order",
            "where id = #{id,jdbcType=BIGINT}"
    })
    @Results({
            @Result(column = "id", property = "id", jdbcType = JdbcType.BIGINT, id = true),
            @Result(column = "uuid", property = "uuid", jdbcType = JdbcType.CHAR),
            @Result(column = "customer_id", property = "customerId", jdbcType = JdbcType.BIGINT),
            @Result(column = "title", property = "title", jdbcType = JdbcType.VARCHAR),
            @Result(column = "ticket_number", property = "ticketNumber", jdbcType = JdbcType.BIGINT),
            @Result(column = "amount", property = "amount", jdbcType = JdbcType.INTEGER),
            @Result(column = "status", property = "status", jdbcType = JdbcType.CHAR),
            @Result(column = "reason", property = "reason", jdbcType = JdbcType.VARCHAR)
    })
    MdOrder selectByPrimaryKey(Long id);

    @Select({
            "select",
            "id, uuid, customer_id, title, ticket_number, amount, status, reason",
            "from md_order",
            "where uuid = #{uuid,jdbcType=CHAR}"
    })
    @Results({
            @Result(column = "id", property = "id", jdbcType = JdbcType.BIGINT, id = true),
            @Result(column = "uuid", property = "uuid", jdbcType = JdbcType.CHAR),
            @Result(column = "customer_id", property = "customerId", jdbcType = JdbcType.BIGINT),
            @Result(column = "title", property = "title", jdbcType = JdbcType.VARCHAR),
            @Result(column = "ticket_number", property = "ticketNumber", jdbcType = JdbcType.BIGINT),
            @Result(column = "amount", property = "amount", jdbcType = JdbcType.INTEGER),
            @Result(column = "status", property = "status", jdbcType = JdbcType.CHAR),
            @Result(column = "reason", property = "reason", jdbcType = JdbcType.VARCHAR)
    })
    MdOrder selectByUuid(String uuid);

    @Select({
            "select",
            "id, uuid, customer_id, title, ticket_number, amount, status, reason",
            "from md_order",
            "where customer_id = #{customerId,jdbcType=BIGINT}"
    })
    @Results({
            @Result(column = "id", property = "id", jdbcType = JdbcType.BIGINT, id = true),
            @Result(column = "uuid", property = "uuid", jdbcType = JdbcType.CHAR),
            @Result(column = "customer_id", property = "customerId", jdbcType = JdbcType.BIGINT),
            @Result(column = "title", property = "title", jdbcType = JdbcType.VARCHAR),
            @Result(column = "ticket_number", property = "ticketNumber", jdbcType = JdbcType.BIGINT),
            @Result(column = "amount", property = "amount", jdbcType = JdbcType.INTEGER),
            @Result(column = "status", property = "status", jdbcType = JdbcType.CHAR),
            @Result(column = "reason", property = "reason", jdbcType = JdbcType.VARCHAR)
    })
    List<MdOrder> selectByCustomerId(Long customerId);

    @Select({
            "select",
            "id, uuid, customer_id, title, ticket_number, amount, status, reason",
            "from md_order"
    })
    @Results({
            @Result(column = "id", property = "id", jdbcType = JdbcType.BIGINT, id = true),
            @Result(column = "uuid", property = "uuid", jdbcType = JdbcType.CHAR),
            @Result(column = "customer_id", property = "customerId", jdbcType = JdbcType.BIGINT),
            @Result(column = "title", property = "title", jdbcType = JdbcType.VARCHAR),
            @Result(column = "ticket_number", property = "ticketNumber", jdbcType = JdbcType.BIGINT),
            @Result(column = "amount", property = "amount", jdbcType = JdbcType.INTEGER),
            @Result(column = "status", property = "status", jdbcType = JdbcType.CHAR),
            @Result(column = "reason", property = "reason", jdbcType = JdbcType.VARCHAR)
    })
    List<MdOrder> selectAll();

    @UpdateProvider(type = MdOrderSqlProvider.class, method = "updateByPrimaryKeySelective")
    int updateByPrimaryKeySelective(MdOrder record);

    @Update({
            "update md_order",
            "set uuid = #{uuid,jdbcType=CHAR},",
            "customer_id = #{customerId,jdbcType=BIGINT},",
            "title = #{title,jdbcType=VARCHAR},",
            "ticket_number = #{ticketNumber,jdbcType=BIGINT},",
            "amount = #{amount,jdbcType=INTEGER},",
            "status = #{status,jdbcType=CHAR},",
            "reason = #{reason,jdbcType=VARCHAR}",
            "where id = #{id,jdbcType=BIGINT}"
    })
    int updateByPrimaryKey(MdOrder record);
}