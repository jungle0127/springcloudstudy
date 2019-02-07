package com.ps.infrastructure.database.multiple.dynamic.datasource.dao.storage.mapper;

import com.ps.infrastructure.database.multiple.dynamic.datasource.dao.storage.model.TStorage;
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

public interface TStorageMapper {
    @Delete({
        "delete from t_storage",
        "where id = #{id,jdbcType=BIGINT}"
    })
    int deleteByPrimaryKey(Long id);

    @Insert({
        "insert into t_storage (id, product_name, ",
        "inventory, create_time, ",
        "update_time)",
        "values (#{id,jdbcType=BIGINT}, #{productName,jdbcType=VARCHAR}, ",
        "#{inventory,jdbcType=INTEGER}, #{createTime,jdbcType=TIMESTAMP}, ",
        "#{updateTime,jdbcType=TIMESTAMP})"
    })
    int insert(TStorage record);

    @InsertProvider(type=TStorageSqlProvider.class, method="insertSelective")
    int insertSelective(TStorage record);

    @Select({
        "select",
        "id, product_name, inventory, create_time, update_time",
        "from t_storage",
        "where id = #{id,jdbcType=BIGINT}"
    })
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.BIGINT, id=true),
        @Result(column="product_name", property="productName", jdbcType=JdbcType.VARCHAR),
        @Result(column="inventory", property="inventory", jdbcType=JdbcType.INTEGER),
        @Result(column="create_time", property="createTime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="update_time", property="updateTime", jdbcType=JdbcType.TIMESTAMP)
    })
    TStorage selectByPrimaryKey(Long id);
    @Select({
            "select",
            "id, product_name, inventory, create_time, update_time",
            "from t_storage"
    })
    @Results({
            @Result(column="id", property="id", jdbcType=JdbcType.BIGINT, id=true),
            @Result(column="product_name", property="productName", jdbcType=JdbcType.VARCHAR),
            @Result(column="inventory", property="inventory", jdbcType=JdbcType.INTEGER),
            @Result(column="create_time", property="createTime", jdbcType=JdbcType.TIMESTAMP),
            @Result(column="update_time", property="updateTime", jdbcType=JdbcType.TIMESTAMP)
    })
    List<TStorage> selectAll();
    @UpdateProvider(type=TStorageSqlProvider.class, method="updateByPrimaryKeySelective")
    int updateByPrimaryKeySelective(TStorage record);

    @Update({
        "update t_storage",
        "set product_name = #{productName,jdbcType=VARCHAR},",
          "inventory = #{inventory,jdbcType=INTEGER},",
          "create_time = #{createTime,jdbcType=TIMESTAMP},",
          "update_time = #{updateTime,jdbcType=TIMESTAMP}",
        "where id = #{id,jdbcType=BIGINT}"
    })
    int updateByPrimaryKey(TStorage record);
}