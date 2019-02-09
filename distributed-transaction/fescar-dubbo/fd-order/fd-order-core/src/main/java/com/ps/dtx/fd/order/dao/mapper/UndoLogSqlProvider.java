package com.ps.dtx.fd.order.dao.mapper;

import com.ps.dtx.fd.order.dao.model.UndoLog;
import org.apache.ibatis.jdbc.SQL;

public class UndoLogSqlProvider {

    public String insertSelective(UndoLog record) {
        SQL sql = new SQL();
        sql.INSERT_INTO("undo_log");
        
        if (record.getId() != null) {
            sql.VALUES("id", "#{id,jdbcType=BIGINT}");
        }
        
        if (record.getBranchId() != null) {
            sql.VALUES("branch_id", "#{branchId,jdbcType=BIGINT}");
        }
        
        if (record.getXid() != null) {
            sql.VALUES("xid", "#{xid,jdbcType=VARCHAR}");
        }
        
        if (record.getLogStatus() != null) {
            sql.VALUES("log_status", "#{logStatus,jdbcType=INTEGER}");
        }
        
        if (record.getLogCreated() != null) {
            sql.VALUES("log_created", "#{logCreated,jdbcType=TIMESTAMP}");
        }
        
        if (record.getLogModified() != null) {
            sql.VALUES("log_modified", "#{logModified,jdbcType=TIMESTAMP}");
        }
        
        if (record.getExt() != null) {
            sql.VALUES("ext", "#{ext,jdbcType=VARCHAR}");
        }
        
        if (record.getRollbackInfo() != null) {
            sql.VALUES("rollback_info", "#{rollbackInfo,jdbcType=LONGVARBINARY}");
        }
        
        return sql.toString();
    }

    public String updateByPrimaryKeySelective(UndoLog record) {
        SQL sql = new SQL();
        sql.UPDATE("undo_log");
        
        if (record.getBranchId() != null) {
            sql.SET("branch_id = #{branchId,jdbcType=BIGINT}");
        }
        
        if (record.getXid() != null) {
            sql.SET("xid = #{xid,jdbcType=VARCHAR}");
        }
        
        if (record.getLogStatus() != null) {
            sql.SET("log_status = #{logStatus,jdbcType=INTEGER}");
        }
        
        if (record.getLogCreated() != null) {
            sql.SET("log_created = #{logCreated,jdbcType=TIMESTAMP}");
        }
        
        if (record.getLogModified() != null) {
            sql.SET("log_modified = #{logModified,jdbcType=TIMESTAMP}");
        }
        
        if (record.getExt() != null) {
            sql.SET("ext = #{ext,jdbcType=VARCHAR}");
        }
        
        if (record.getRollbackInfo() != null) {
            sql.SET("rollback_info = #{rollbackInfo,jdbcType=LONGVARBINARY}");
        }
        
        sql.WHERE("id = #{id,jdbcType=BIGINT}");
        
        return sql.toString();
    }
}