package com.ps.dtx.fd.order.dao.mapper;

import com.ps.dtx.fd.order.dao.model.OrderTbl;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.apache.ibatis.annotations.UpdateProvider;
import org.apache.ibatis.type.JdbcType;

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