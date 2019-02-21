package com.ps.dtx.md.storage.dao.mapper;

import com.ps.dtx.md.storage.dao.model.StorageTbl;
import org.apache.ibatis.annotations.*;
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
    @Select({
            "select",
            "id, commodity_code, count",
            "from storage_tbl",
            "where commodity_code = #{commodityCode,jdbcType=VARCHAR}"
    })
    @Results({
            @Result(column="id", property="id", jdbcType=JdbcType.INTEGER, id=true),
            @Result(column="commodity_code", property="commodityCode", jdbcType=JdbcType.VARCHAR),
            @Result(column="count", property="count", jdbcType=JdbcType.INTEGER)
    })
    StorageTbl selectByCommodityCode(String commodityCode);
    @UpdateProvider(type=StorageTblSqlProvider.class, method="updateByPrimaryKeySelective")
    int updateByPrimaryKeySelective(StorageTbl record);

    @Update({
        "update storage_tbl",
        "set commodity_code = #{commodityCode,jdbcType=VARCHAR},",
          "count = #{count,jdbcType=INTEGER}",
        "where id = #{id,jdbcType=INTEGER}"
    })
    int updateByPrimaryKey(StorageTbl record);
    @Update({
            "update storage_tbl",
            "set count = count - #{count,jdbcType=INTEGER}",
            "where commodity_code = #{commodityCode, jdbcType=VARCHAR}"
    })
    int updateInventory(String commodityCode, Integer count);
}