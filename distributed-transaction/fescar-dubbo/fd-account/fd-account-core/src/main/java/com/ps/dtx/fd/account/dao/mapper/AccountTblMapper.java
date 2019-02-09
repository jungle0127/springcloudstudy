package com.ps.dtx.fd.account.dao.mapper;

import com.ps.dtx.fd.account.dao.model.AccountTbl;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.apache.ibatis.annotations.UpdateProvider;
import org.apache.ibatis.type.JdbcType;

public interface AccountTblMapper {
    @Delete({
        "delete from account_tbl",
        "where id = #{id,jdbcType=INTEGER}"
    })
    int deleteByPrimaryKey(Integer id);

    @Insert({
        "insert into account_tbl (id, user_id, ",
        "money)",
        "values (#{id,jdbcType=INTEGER}, #{userId,jdbcType=VARCHAR}, ",
        "#{money,jdbcType=INTEGER})"
    })
    int insert(AccountTbl record);

    @InsertProvider(type=AccountTblSqlProvider.class, method="insertSelective")
    int insertSelective(AccountTbl record);

    @Select({
        "select",
        "id, user_id, money",
        "from account_tbl",
        "where id = #{id,jdbcType=INTEGER}"
    })
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="user_id", property="userId", jdbcType=JdbcType.VARCHAR),
        @Result(column="money", property="money", jdbcType=JdbcType.INTEGER)
    })
    AccountTbl selectByPrimaryKey(Integer id);

    @UpdateProvider(type=AccountTblSqlProvider.class, method="updateByPrimaryKeySelective")
    int updateByPrimaryKeySelective(AccountTbl record);

    @Update({
        "update account_tbl",
        "set user_id = #{userId,jdbcType=VARCHAR},",
          "money = #{money,jdbcType=INTEGER}",
        "where id = #{id,jdbcType=INTEGER}"
    })
    int updateByPrimaryKey(AccountTbl record);
}