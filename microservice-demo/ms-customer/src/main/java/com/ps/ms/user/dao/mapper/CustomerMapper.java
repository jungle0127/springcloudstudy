package com.ps.ms.user.dao.mapper;

import com.ps.ms.user.dao.model.Customer;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.type.JdbcType;

import java.util.List;

public interface CustomerMapper {
    @Delete({
            "delete from customer",
            "where id = #{id,jdbcType=BIGINT}"
    })
    int deleteByPrimaryKey(Long id);

    @Insert({
            "insert into customer (id, username, ",
            "password, deposit)",
            "values (#{id,jdbcType=BIGINT}, #{username,jdbcType=VARCHAR}, ",
            "#{password,jdbcType=VARCHAR}, #{deposit,jdbcType=INTEGER})"
    })
    int insert(Customer record);

    @InsertProvider(type = CustomerSqlProvider.class, method = "insertSelective")
    int insertSelective(Customer record);

    @Select({
            "select",
            "id, username, password, deposit",
            "from customer",
            "where id = #{id,jdbcType=BIGINT}"
    })
    @Results({
            @Result(column = "id", property = "id", jdbcType = JdbcType.BIGINT, id = true),
            @Result(column = "username", property = "username", jdbcType = JdbcType.VARCHAR),
            @Result(column = "password", property = "password", jdbcType = JdbcType.VARCHAR),
            @Result(column = "deposit", property = "deposit", jdbcType = JdbcType.INTEGER)
    })
    Customer selectByPrimaryKey(Long id);

    @Select({
            "select",
            "id, username, password, deposit",
            "from customer"
    })
    @Results({
            @Result(column = "id", property = "id", jdbcType = JdbcType.BIGINT, id = true),
            @Result(column = "username", property = "username", jdbcType = JdbcType.VARCHAR),
            @Result(column = "password", property = "password", jdbcType = JdbcType.VARCHAR),
            @Result(column = "deposit", property = "deposit", jdbcType = JdbcType.INTEGER)
    })
    List<Customer> selectAll();

    @Select({
            "select",
            "id, username, password, deposit",
            "from customer",
            "where username = #{userName,jdbcType=VARCHAR}"
    })
    @Results({
            @Result(column = "id", property = "id", jdbcType = JdbcType.BIGINT, id = true),
            @Result(column = "username", property = "username", jdbcType = JdbcType.VARCHAR),
            @Result(column = "password", property = "password", jdbcType = JdbcType.VARCHAR),
            @Result(column = "deposit", property = "deposit", jdbcType = JdbcType.INTEGER)
    })
    Customer selectByUsername(String userName);

    @UpdateProvider(type = CustomerSqlProvider.class, method = "updateByPrimaryKeySelective")
    int updateByPrimaryKeySelective(Customer record);

    @Update({
            "update customer",
            "set username = #{username,jdbcType=VARCHAR},",
            "password = #{password,jdbcType=VARCHAR},",
            "deposit = #{deposit,jdbcType=INTEGER}",
            "where id = #{id,jdbcType=BIGINT}"
    })
    int updateByPrimaryKey(Customer record);
}