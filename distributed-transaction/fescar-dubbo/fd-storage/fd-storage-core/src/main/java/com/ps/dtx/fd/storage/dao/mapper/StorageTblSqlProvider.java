package com.ps.dtx.fd.storage.dao.mapper;

import static org.apache.ibatis.jdbc.SqlBuilder.BEGIN;
import static org.apache.ibatis.jdbc.SqlBuilder.INSERT_INTO;
import static org.apache.ibatis.jdbc.SqlBuilder.SET;
import static org.apache.ibatis.jdbc.SqlBuilder.SQL;
import static org.apache.ibatis.jdbc.SqlBuilder.UPDATE;
import static org.apache.ibatis.jdbc.SqlBuilder.VALUES;
import static org.apache.ibatis.jdbc.SqlBuilder.WHERE;

import com.ps.dtx.fd.storage.dao.model.StorageTbl;

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