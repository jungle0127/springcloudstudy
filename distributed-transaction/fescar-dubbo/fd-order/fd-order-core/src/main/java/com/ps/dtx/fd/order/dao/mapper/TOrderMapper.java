package com.ps.dtx.fd.order.dao.mapper;

import com.ps.dtx.fd.order.dao.model.TOrder;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.apache.ibatis.annotations.UpdateProvider;
import org.apache.ibatis.type.JdbcType;

public interface TOrderMapper {
    @Delete({
        "delete from t_order",
        "where id = #{id,jdbcType=BIGINT}"
    })
    int deleteByPrimaryKey(Long id);

    @Insert({
        "insert into t_order (id, customer_id, ",
        "storage_id, amount, ",
        "order_desc, create_time)",
        "values (#{id,jdbcType=BIGINT}, #{customerId,jdbcType=BIGINT}, ",
        "#{storageId,jdbcType=BIGINT}, #{amount,jdbcType=INTEGER}, ",
        "#{orderDesc,jdbcType=VARCHAR}, #{createTime,jdbcType=TIMESTAMP})"
    })
    int insert(TOrder record);

    @InsertProvider(type=TOrderSqlProvider.class, method="insertSelective")
    int insertSelective(TOrder record);

    @Select({
        "select",
        "id, customer_id, storage_id, amount, order_desc, create_time",
        "from t_order",
        "where id = #{id,jdbcType=BIGINT}"
    })
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.BIGINT, id=true),
        @Result(column="customer_id", property="customerId", jdbcType=JdbcType.BIGINT),
        @Result(column="storage_id", property="storageId", jdbcType=JdbcType.BIGINT),
        @Result(column="amount", property="amount", jdbcType=JdbcType.INTEGER),
        @Result(column="order_desc", property="orderDesc", jdbcType=JdbcType.VARCHAR),
        @Result(column="create_time", property="createTime", jdbcType=JdbcType.TIMESTAMP)
    })
    TOrder selectByPrimaryKey(Long id);

    @UpdateProvider(type=TOrderSqlProvider.class, method="updateByPrimaryKeySelective")
    int updateByPrimaryKeySelective(TOrder record);

    @Update({
        "update t_order",
        "set customer_id = #{customerId,jdbcType=BIGINT},",
          "storage_id = #{storageId,jdbcType=BIGINT},",
          "amount = #{amount,jdbcType=INTEGER},",
          "order_desc = #{orderDesc,jdbcType=VARCHAR},",
          "create_time = #{createTime,jdbcType=TIMESTAMP}",
        "where id = #{id,jdbcType=BIGINT}"
    })
    int updateByPrimaryKey(TOrder record);
}