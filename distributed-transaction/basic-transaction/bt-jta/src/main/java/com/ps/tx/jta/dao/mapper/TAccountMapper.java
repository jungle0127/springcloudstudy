package com.ps.tx.jta.dao.mapper;

import com.ps.tx.jta.dao.model.TAccount;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.apache.ibatis.annotations.UpdateProvider;
import org.apache.ibatis.type.JdbcType;

public interface TAccountMapper {
    @Delete({
        "delete from t_account",
        "where id = #{id,jdbcType=BIGINT}"
    })
    int deleteByPrimaryKey(Long id);

    @Insert({
        "insert into t_account (id, customer_id, ",
        "deposit, create_time, ",
        "update_time)",
        "values (#{id,jdbcType=BIGINT}, #{customerId,jdbcType=BIGINT}, ",
        "#{deposit,jdbcType=INTEGER}, #{createTime,jdbcType=TIMESTAMP}, ",
        "#{updateTime,jdbcType=TIMESTAMP})"
    })
    int insert(TAccount record);

    @InsertProvider(type=TAccountSqlProvider.class, method="insertSelective")
    int insertSelective(TAccount record);

    @Select({
        "select",
        "id, customer_id, deposit, create_time, update_time",
        "from t_account",
        "where id = #{id,jdbcType=BIGINT}"
    })
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.BIGINT, id=true),
        @Result(column="customer_id", property="customerId", jdbcType=JdbcType.BIGINT),
        @Result(column="deposit", property="deposit", jdbcType=JdbcType.INTEGER),
        @Result(column="create_time", property="createTime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="update_time", property="updateTime", jdbcType=JdbcType.TIMESTAMP)
    })
    TAccount selectByPrimaryKey(Long id);

    @UpdateProvider(type=TAccountSqlProvider.class, method="updateByPrimaryKeySelective")
    int updateByPrimaryKeySelective(TAccount record);

    @Update({
        "update t_account",
        "set customer_id = #{customerId,jdbcType=BIGINT},",
          "deposit = #{deposit,jdbcType=INTEGER},",
          "create_time = #{createTime,jdbcType=TIMESTAMP},",
          "update_time = #{updateTime,jdbcType=TIMESTAMP}",
        "where id = #{id,jdbcType=BIGINT}"
    })
    int updateByPrimaryKey(TAccount record);
}