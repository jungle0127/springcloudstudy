package com.ps.dtx.md.order.dao.mapper;

import com.ps.dtx.md.order.dao.model.OrderTbl;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.type.JdbcType;

import java.util.List;

public interface OrderTblMapper {
    @Delete({
        "delete from order_tbl",
        "where id = #{id,jdbcType=INTEGER}"
    })
    int deleteByPrimaryKey(Integer id);

    @Insert({
        "insert into order_tbl (id, user_id, ",
        "commodity_code, count, ",
        "money)",
        "values (#{id,jdbcType=INTEGER}, #{userId,jdbcType=VARCHAR}, ",
        "#{commodityCode,jdbcType=VARCHAR}, #{count,jdbcType=INTEGER}, ",
        "#{money,jdbcType=INTEGER})"
    })
    int insert(OrderTbl record);

    @InsertProvider(type=OrderTblSqlProvider.class, method="insertSelective")
    int insertSelective(OrderTbl record);

    @Select({
        "select",
        "id, user_id, commodity_code, count, money",
        "from order_tbl",
        "where id = #{id,jdbcType=INTEGER}"
    })
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="user_id", property="userId", jdbcType=JdbcType.VARCHAR),
        @Result(column="commodity_code", property="commodityCode", jdbcType=JdbcType.VARCHAR),
        @Result(column="count", property="count", jdbcType=JdbcType.INTEGER),
        @Result(column="money", property="money", jdbcType=JdbcType.INTEGER)
    })
    OrderTbl selectByPrimaryKey(Integer id);
    @Select({
            "select id, user_id, commodity_code, count, money ",
            "from order_tbl",
            "where user_id = #{id, jdbcType=INTEGER}"
    })
    @Results({
            @Result(column="id", property="id", jdbcType=JdbcType.INTEGER, id=true),
            @Result(column="user_id", property="userId", jdbcType=JdbcType.VARCHAR),
            @Result(column="commodity_code", property="commodityCode", jdbcType=JdbcType.VARCHAR),
            @Result(column="count", property="count", jdbcType=JdbcType.INTEGER),
            @Result(column="money", property="money", jdbcType=JdbcType.INTEGER)
    })
    List<OrderTbl> selectByUserId(String userId);

    @UpdateProvider(type=OrderTblSqlProvider.class, method="updateByPrimaryKeySelective")
    int updateByPrimaryKeySelective(OrderTbl record);

    @Update({
        "update order_tbl",
        "set user_id = #{userId,jdbcType=VARCHAR},",
          "commodity_code = #{commodityCode,jdbcType=VARCHAR},",
          "count = #{count,jdbcType=INTEGER},",
          "money = #{money,jdbcType=INTEGER}",
        "where id = #{id,jdbcType=INTEGER}"
    })
    int updateByPrimaryKey(OrderTbl record);
}