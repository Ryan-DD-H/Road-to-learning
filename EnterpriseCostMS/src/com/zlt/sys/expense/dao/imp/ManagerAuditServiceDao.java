package com.zlt.sys.expense.dao.imp;

import com.zlt.pojo.AuditRecord;
import com.zlt.sys.expense.dao.IManagerAuditDao;
import com.zlt.utiles.C3p0Util;
import org.apache.commons.dbutils.QueryRunner;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class ManagerAuditServiceDao implements IManagerAuditDao {


    /**
     * 用于报销单的审批
     * @author 黄国旺
     * @param auditRecord 审批记录表的信息
     * @return 无异常则返回更新的条数，否则返回0
     * */
    @Override
    public int addAudit(AuditRecord auditRecord) {
        Connection conn = null;
        int i = 0;
        try {
            conn = C3p0Util.getConn();
            conn.setAutoCommit(false);
            //添加审批记录表
            String sql1 = "INSERT INTO t_audit_record VALUES (?,?,?,?,?,now())";
            QueryRunner queryRunner = new QueryRunner();
            i += queryRunner.update(conn,sql1,auditRecord.getAuditId(),auditRecord.getExpenseId(),auditRecord.getUsersId(),auditRecord.getAuditState(),auditRecord.getAuditSuggest());
            //更新对应的报销单的id报销表的状态
            String sql2 = "UPDATE t_expense SET expenseState = ? WHERE expenseId = ?";
            i += queryRunner.update(conn,sql2,auditRecord.getAuditState(),auditRecord.getExpenseId());

            conn.commit();
            conn.setAutoCommit(true);
            return i;
        } catch (SQLException e) {
            try {
                conn.rollback();
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
            e.printStackTrace();
            return 0;
        }
    }

    /**
     * 查询审批记录
     * @author 黄国旺
     * @param expenseId 报销单的id
     * @return 返回对应报销单id的审批记录
     * */
    @Override
    public List<AuditRecord> selectAuditRecord(Integer expenseId) {

        String sql = "SELECT ar.*,u.usersName FROM t_audit_record ar INNER JOIN t_users u ON ar.usersId = u.usersId AND ar.expenseId = ?";
        try {
            return C3p0Util.queryList(sql,AuditRecord.class,expenseId);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
}
