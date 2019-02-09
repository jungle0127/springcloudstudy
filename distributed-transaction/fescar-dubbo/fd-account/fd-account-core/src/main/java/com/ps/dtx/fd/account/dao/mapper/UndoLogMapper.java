package com.ps.dtx.fd.account.dao.mapper;

import com.ps.dtx.fd.account.dao.model.UndoLog;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.apache.ibatis.annotations.UpdateProvider;
import org.apache.ibatis.type.JdbcType;

public interface UndoLogMapper {
    @Delete({
        "delete from undo_log",
        "where id = #{id,jdbcType=BIGINT}"
    })
    int deleteByPrimaryKey(Long id);

    @Insert({
        "insert into undo_log (id, branch_id, ",
        "xid, log_status, ",
        "log_created, log_modified, ",
        "ext, rollback_info)",
        "values (#{id,jdbcType=BIGINT}, #{branchId,jdbcType=BIGINT}, ",
        "#{xid,jdbcType=VARCHAR}, #{logStatus,jdbcType=INTEGER}, ",
        "#{logCreated,jdbcType=TIMESTAMP}, #{logModified,jdbcType=TIMESTAMP}, ",
        "#{ext,jdbcType=VARCHAR}, #{rollbackInfo,jdbcType=LONGVARBINARY})"
    })
    int insert(UndoLog record);

    @InsertProvider(type=UndoLogSqlProvider.class, method="insertSelective")
    int insertSelective(UndoLog record);

    @Select({
        "select",
        "id, branch_id, xid, log_status, log_created, log_modified, ext, rollback_info",
        "from undo_log",
        "where id = #{id,jdbcType=BIGINT}"
    })
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.BIGINT, id=true),
        @Result(column="branch_id", property="branchId", jdbcType=JdbcType.BIGINT),
        @Result(column="xid", property="xid", jdbcType=JdbcType.VARCHAR),
        @Result(column="log_status", property="logStatus", jdbcType=JdbcType.INTEGER),
        @Result(column="log_created", property="logCreated", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="log_modified", property="logModified", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="ext", property="ext", jdbcType=JdbcType.VARCHAR),
        @Result(column="rollback_info", property="rollbackInfo", jdbcType=JdbcType.LONGVARBINARY)
    })
    UndoLog selectByPrimaryKey(Long id);

    @UpdateProvider(type=UndoLogSqlProvider.class, method="updateByPrimaryKeySelective")
    int updateByPrimaryKeySelective(UndoLog record);

    @Update({
        "update undo_log",
        "set branch_id = #{branchId,jdbcType=BIGINT},",
          "xid = #{xid,jdbcType=VARCHAR},",
          "log_status = #{logStatus,jdbcType=INTEGER},",
          "log_created = #{logCreated,jdbcType=TIMESTAMP},",
          "log_modified = #{logModified,jdbcType=TIMESTAMP},",
          "ext = #{ext,jdbcType=VARCHAR},",
          "rollback_info = #{rollbackInfo,jdbcType=LONGVARBINARY}",
        "where id = #{id,jdbcType=BIGINT}"
    })
    int updateByPrimaryKeyWithBLOBs(UndoLog record);

    @Update({
        "update undo_log",
        "set branch_id = #{branchId,jdbcType=BIGINT},",
          "xid = #{xid,jdbcType=VARCHAR},",
          "log_status = #{logStatus,jdbcType=INTEGER},",
          "log_created = #{logCreated,jdbcType=TIMESTAMP},",
          "log_modified = #{logModified,jdbcType=TIMESTAMP},",
          "ext = #{ext,jdbcType=VARCHAR}",
        "where id = #{id,jdbcType=BIGINT}"
    })
    int updateByPrimaryKey(UndoLog record);
}