package com.ps.tx.md.user.dao.mapper;

import com.ps.tx.md.user.dao.model.MdCustomer;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.apache.ibatis.annotations.UpdateProvider;
import org.apache.ibatis.type.JdbcType;

import java.util.List;

public interface MdCustomerMapper {
    @Delete({
        "delete from md_customer",
        "where id = #{id,jdbcType=BIGINT}"
    })
    int deleteByPrimaryKey(Long id);

    @Insert({
        "insert into md_customer (id, username, ",
        "password, role, ",
        "deposit)",
        "values (#{id,jdbcType=BIGINT}, #{username,jdbcType=VARCHAR}, ",
        "#{password,jdbcType=VARCHAR}, #{role,jdbcType=VARCHAR}, ",
        "#{deposit,jdbcType=INTEGER})"
    })
    int insert(MdCustomer record);

    @InsertProvider(type=MdCustomerSqlProvider.class, method="insertSelective")
    int insertSelective(MdCustomer record);

    @Select({
        "select",
        "id, username, password, role, deposit",
        "from md_customer",
        "where id = #{id,jdbcType=BIGINT}"
    })
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.BIGINT, id=true),
        @Result(column="username", property="username", jdbcType=JdbcType.VARCHAR),
        @Result(column="password", property="password", jdbcType=JdbcType.VARCHAR),
        @Result(column="role", property="role", jdbcType=JdbcType.VARCHAR),
        @Result(column="deposit", property="deposit", jdbcType=JdbcType.INTEGER)
    })
    MdCustomer selectByPrimaryKey(Long id);

    @Select({
            "select",
            "id, username, password, role, deposit",
            "from md_customer"
    })
    @Results({
            @Result(column="id", property="id", jdbcType=JdbcType.BIGINT, id=true),
            @Result(column="username", property="username", jdbcType=JdbcType.VARCHAR),
            @Result(column="password", property="password", jdbcType=JdbcType.VARCHAR),
            @Result(column="role", property="role", jdbcType=JdbcType.VARCHAR),
            @Result(column="deposit", property="deposit", jdbcType=JdbcType.INTEGER)
    })
    List<MdCustomer> selectAll();
    @Select({
            "select",
            "id, username, password, role, deposit",
            "from md_customer",
            "where username = #{userName,jdbcType=VARCHAR}"
    })
    @Results({
            @Result(column="id", property="id", jdbcType=JdbcType.BIGINT, id=true),
            @Result(column="username", property="username", jdbcType=JdbcType.VARCHAR),
            @Result(column="password", property="password", jdbcType=JdbcType.VARCHAR),
            @Result(column="role", property="role", jdbcType=JdbcType.VARCHAR),
            @Result(column="deposit", property="deposit", jdbcType=JdbcType.INTEGER)
    })
    MdCustomer selectByUsername(String userName);
    @UpdateProvider(type=MdCustomerSqlProvider.class, method="updateByPrimaryKeySelective")
    int updateByPrimaryKeySelective(MdCustomer record);

    @Update({
        "update md_customer",
        "set username = #{username,jdbcType=VARCHAR},",
          "password = #{password,jdbcType=VARCHAR},",
          "role = #{role,jdbcType=VARCHAR},",
          "deposit = #{deposit,jdbcType=INTEGER}",
        "where id = #{id,jdbcType=BIGINT}"
    })
    int updateByPrimaryKey(MdCustomer record);
}