package com.ps.dtx.fd.storage.dao.mapper;

import com.ps.dtx.fd.storage.dao.model.StorageTbl;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.apache.ibatis.annotations.UpdateProvider;
import org.apache.ibatis.type.JdbcType;

public interface StorageTblMapper {
    @Delete({
        "delete from storage_tbl",
        "where id = #{id,jdbcType=INTEGER}"
    })
    int deleteByPrimaryKey(Integer id);

    @Insert({
        "insert into storage_tbl (id, commodity_code, ",
        "count)",
        "values (#{id,jdbcType=INTEGER}, #{commodityCode,jdbcType=VARCHAR}, ",
        "#{count,jdbcType=INTEGER})"
    })
    int insert(StorageTbl record);

    @InsertProvider(type=StorageTblSqlProvider.class, method="insertSelective")
    int insertSelective(StorageTbl record);

    @Select({
        "select",
        "id, commodity_code, count",
        "from storage_tbl",
        "where id = #{id,jdbcType=INTEGER}"
    })
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="commodity_code", property="commodityCode", jdbcType=JdbcType.VARCHAR),
        @Result(column="count", property="count", jdbcType=JdbcType.INTEGER)
    })
    StorageTbl selectByPrimaryKey(Integer id);

    @UpdateProvider(type=StorageTblSqlProvider.class, method="updateByPrimaryKeySelective")
    int updateByPrimaryKeySelective(StorageTbl record);

    @Update({
        "update storage_tbl",
        "set commodity_code = #{commodityCode,jdbcType=VARCHAR},",
          "count = #{count,jdbcType=INTEGER}",
        "where id = #{id,jdbcType=INTEGER}"
    })
    int updateByPrimaryKey(StorageTbl record);
}