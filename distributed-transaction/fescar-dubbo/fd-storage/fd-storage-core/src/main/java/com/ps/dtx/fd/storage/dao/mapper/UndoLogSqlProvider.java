package com.ps.dtx.fd.storage.dao.mapper;

import static org.apache.ibatis.jdbc.SqlBuilder.BEGIN;
import static org.apache.ibatis.jdbc.SqlBuilder.INSERT_INTO;
import static org.apache.ibatis.jdbc.SqlBuilder.SET;
import static org.apache.ibatis.jdbc.SqlBuilder.SQL;
import static org.apache.ibatis.jdbc.SqlBuilder.UPDATE;
import static org.apache.ibatis.jdbc.SqlBuilder.VALUES;
import static org.apache.ibatis.jdbc.SqlBuilder.WHERE;

import com.ps.dtx.fd.storage.dao.model.UndoLog;

public class UndoLogSqlProvider {

    public String insertSelective(UndoLog record) {
        BEGIN();
        INSERT_INTO("undo_log");
        
        if (record.getId() != null) {
            VALUES("id", "#{id,jdbcType=BIGINT}");
        }
        
        if (record.getBranchId() != null) {
            VALUES("branch_id", "#{branchId,jdbcType=BIGINT}");
        }
        
        if (record.getXid() != null) {
            VALUES("xid", "#{xid,jdbcType=VARCHAR}");
        }
        
        if (record.getLogStatus() != null) {
            VALUES("log_status", "#{logStatus,jdbcType=INTEGER}");
        }
        
        if (record.getLogCreated() != null) {
            VALUES("log_created", "#{logCreated,jdbcType=TIMESTAMP}");
        }
        
        if (record.getLogModified() != null) {
            VALUES("log_modified", "#{logModified,jdbcType=TIMESTAMP}");
        }
        
        if (record.getExt() != null) {
            VALUES("ext", "#{ext,jdbcType=VARCHAR}");
        }
        
        if (record.getRollbackInfo() != null) {
            VALUES("rollback_info", "#{rollbackInfo,jdbcType=LONGVARBINARY}");
        }
        
        return SQL();
    }

    public String updateByPrimaryKeySelective(UndoLog record) {
        BEGIN();
        UPDATE("undo_log");
        
        if (record.getBranchId() != null) {
            SET("branch_id = #{branchId,jdbcType=BIGINT}");
        }
        
        if (record.getXid() != null) {
            SET("xid = #{xid,jdbcType=VARCHAR}");
        }
        
        if (record.getLogStatus() != null) {
            SET("log_status = #{logStatus,jdbcType=INTEGER}");
        }
        
        if (record.getLogCreated() != null) {
            SET("log_created = #{logCreated,jdbcType=TIMESTAMP}");
        }
        
        if (record.getLogModified() != null) {
            SET("log_modified = #{logModified,jdbcType=TIMESTAMP}");
        }
        
        if (record.getExt() != null) {
            SET("ext = #{ext,jdbcType=VARCHAR}");
        }
        
        if (record.getRollbackInfo() != null) {
            SET("rollback_info = #{rollbackInfo,jdbcType=LONGVARBINARY}");
        }
        
        WHERE("id = #{id,jdbcType=BIGINT}");
        
        return SQL();
    }
}