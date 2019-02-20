package com.ps.dtx.md.storage.dao.mapper;


import com.ps.dtx.md.storage.dao.model.StorageTbl;

import static org.apache.ibatis.jdbc.SelectBuilder.SQL;
import static org.apache.ibatis.jdbc.SqlBuilder.*;


public class StorageTblSqlProvider {

    public String insertSelective(StorageTbl record) {
        BEGIN();
        INSERT_INTO("storage_tbl");
        
        if (record.getId() != null) {
            VALUES("id", "#{id,jdbcType=INTEGER}");
        }
        
        if (record.getCommodityCode() != null) {
            VALUES("commodity_code", "#{commodityCode,jdbcType=VARCHAR}");
        }
        
        if (record.getCount() != null) {
            VALUES("count", "#{count,jdbcType=INTEGER}");
        }
        
        return SQL();
    }

    public String updateByPrimaryKeySelective(StorageTbl record) {
        BEGIN();
        UPDATE("storage_tbl");
        
        if (record.getCommodityCode() != null) {
            SET("commodity_code = #{commodityCode,jdbcType=VARCHAR}");
        }
        
        if (record.getCount() != null) {
            SET("count = #{count,jdbcType=INTEGER}");
        }
        
        WHERE("id = #{id,jdbcType=INTEGER}");
        
        return SQL();
    }
}