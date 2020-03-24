package com.zlt.sys.expense.dao;

import com.zlt.pojo.AuditRecord;

import java.util.List;

public interface IManagerAuditDao {
    int addAudit(AuditRecord auditRecord);

    List<AuditRecord> selectAuditRecord(Integer expenseId);
}
