package com.zlt.sys.expense.service;

import com.zlt.pojo.AuditRecord;

import java.util.List;

public interface IManagerAuditService {
    void addAudit(AuditRecord auditRecord);

    List<AuditRecord> selectAuditRecord(Integer expenseId);
}
